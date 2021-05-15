// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.secret;

import aws.proserve.bcs.dr.exception.PortalException;
import aws.proserve.bcs.dr.project.Project;
import aws.proserve.bcs.dr.project.Side;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.CreateSecretRequest;
import com.amazonaws.services.secretsmanager.model.DeleteSecretRequest;
import com.amazonaws.services.secretsmanager.model.DescribeSecretRequest;
import com.amazonaws.services.secretsmanager.model.Filter;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.ListSecretsRequest;
import com.amazonaws.services.secretsmanager.model.PutSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import com.amazonaws.services.secretsmanager.model.Tag;
import com.amazonaws.services.secretsmanager.model.TagResourceRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Named
public class SecretManager {
    private static final String TAG_KEY = "drportal";
    private static final String TAG_VALUE = "tmp";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final AWSSecretsManager manager;
    private final ObjectMapper mapper;

    /**
     * @apiNote <ul>
     * <li>{@code Inject} is for dagger dependency injection.</li>
     * <li>{@code public} is for the {@code new} keyword creation.</li>
     * <li>Also, this only constructor is used by spring to create a new bean.</li>
     * </ul>
     */
    @Inject
    public SecretManager(AWSSecretsManager manager, ObjectMapper mapper) {
        this.manager = manager;
        this.mapper = mapper;
    }

    public void deleteSecret(String id) {
        manager.deleteSecret(new DeleteSecretRequest()
                .withSecretId(id)
                .withForceDeleteWithoutRecovery(true));
    }

    public void deleteTempSecrets() {
        for (var secret : manager.listSecrets(new ListSecretsRequest()
                .withFilters(
                        new Filter().withKey("tag-key").withValues(TAG_KEY),
                        new Filter().withKey("tag-value").withValues(TAG_VALUE)))
                .getSecretList()) {
            deleteSecret(secret.getName());
        }
    }

    @Nullable
    public Credential getCredential(String id) {
        final var map = getSecretMap(id);
        return map == null ? null : new Credential((String) map.get("access"), (String) map.get("secret"));
    }

    @Nullable
    public Credential getCredential(Project project) {
        return getCredential(project, Side.source);
    }

    @Nullable
    public Credential getCredential(Project project, Side side) {
        return getCredentialByProject(project.getId(), side);
    }

    @Nullable
    public Credential getCredentialByProject(String projectId) {
        return getCredentialByProject(projectId, Side.source);
    }

    @Nullable
    public Credential getCredentialByProject(String projectId, Side side) {
        return getCredential(Secrets.idOfAws(projectId, side));
    }

    @Nullable
    public String getSecret(String id) {
        return exists(id)
                ? manager.getSecretValue(new GetSecretValueRequest().withSecretId(id)).getSecretString()
                : null;
    }

    @Nullable
    public Map<String, Object> getSecretMap(String id) {
        final var string = getSecret(id);
        if (string == null || string.isEmpty()) {
            return null;
        }

        try {
            // string cannot be null.
            return (Map<String, Object>) mapper.readValue(string, Map.class);
        } catch (IOException e) {
            log.warn("Unable to parse secret", e);
            return null;
        }
    }

    /**
     * @return the secret ID of the newly saved secret object.
     */
    public String saveSecret(Object secretObject) {
        final var id = "/drportal/tmp/" + UUID.randomUUID().toString();
        saveSecret(id, secretObject);
        manager.tagResource(new TagResourceRequest()
                .withSecretId(id)
                .withTags(new Tag().withKey(TAG_KEY).withValue(TAG_VALUE)));
        return id;
    }

    public void saveSecret(String id, Object secretObject) {
        final String string;

        try {
            string = mapper.writeValueAsString(secretObject);
        } catch (JsonProcessingException e) {
            throw new PortalException("Unable to process json", e);
        }
        saveSecret(id, string);
    }

    public void saveSecret(String id, String secretString) {
        if (exists(id)) {
            manager.putSecretValue(new PutSecretValueRequest()
                    .withSecretId(id)
                    .withSecretString(secretString));
        } else {
            manager.createSecret(new CreateSecretRequest()
                    .withName(id)
                    .withSecretString(secretString));
        }
    }

    private boolean exists(String id) {
        try {
            manager.describeSecret(new DescribeSecretRequest().withSecretId(id));
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }
}
