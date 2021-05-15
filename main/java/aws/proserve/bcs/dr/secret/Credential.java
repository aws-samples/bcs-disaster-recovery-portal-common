// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.secret;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;

@DynamoDBDocument
public final class Credential {
    public static AWSCredentialsProvider toProvider(Credential credential) {
        return credential == null
                || credential.getAccess() == null
                || credential.getAccess().isEmpty()
                || credential.getSecret() == null
                || credential.getSecret().isEmpty()
                ? null : credential.toProvider();
    }

    private String access;
    private String secret;

    public Credential() {
    }

    public Credential(String access, String secret) {
        this.access = access;
        this.secret = secret;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @JsonIgnore
    @DynamoDBIgnore
    public AWSCredentialsProvider toProvider() {
        return access == null ? null : new AWSStaticCredentialsProvider(new BasicAWSCredentials(access, secret));
    }
}
