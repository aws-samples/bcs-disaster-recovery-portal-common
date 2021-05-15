// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.project;

import aws.proserve.bcs.dr.dynamo.DynamoConstants;
import aws.proserve.bcs.dr.exception.ProjectNotFoundException;
import aws.proserve.bcs.dr.secret.Credential;
import aws.proserve.bcs.dr.secret.SecretManager;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class ProjectFinder implements ProjectService {

    private final DynamoDBMapper dbMapper;
    private final SecretManager secretManager;

    // for dagger
    @Inject
    ProjectFinder(DynamoDBMapper dbMapper, SecretManager secretManager) {
        this.dbMapper = dbMapper;
        this.secretManager = secretManager;
    }

    /**
     * @throws ProjectNotFoundException if unable to find the project with the ID
     */
    public Project findOne(String id) throws ProjectNotFoundException {
        final var project = dbMapper.load(Project.class, id);
        if (project == null) {
            throw new ProjectNotFoundException(id);
        } else {
            return project;
        }
    }

    public List<Project> findByType(Component component) {
        return dbMapper.scan(Project.class, new DynamoDBScanExpression()
                .withFilterExpression("#type = :v1")
                .withExpressionAttributeNames(Map.of("#type", DynamoConstants.KEY_TYPE))
                .withExpressionAttributeValues(Map.of(":v1", new AttributeValue().withS(component.getName()))));
    }

    public void save(Project project) {
        save(project, null);
    }

    public void save(Project project, Credential source) {
        dbMapper.save(project);
        if (source != null) {
            secretManager.saveSecret(project.generateSecretId(Side.source), source);
        }
    }

    @Override
    public void delete(Project project) {
        secretManager.deleteSecret(project.generateSecretId(Side.source));
        dbMapper.delete(project);
    }
}
