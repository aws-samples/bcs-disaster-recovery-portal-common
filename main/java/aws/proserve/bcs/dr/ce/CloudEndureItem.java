// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.ce;

import aws.proserve.bcs.dr.dto.HasName;
import aws.proserve.bcs.dr.project.Item;
import aws.proserve.bcs.dr.util.StringArrayListConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

/**
 * @apiNote this class contains properties provided directly by cloud-endure. New properties should not be added here.
 */
public class CloudEndureItem extends Item implements HasName {

    public enum Type {
        MIGRATION;
    }

    public enum State {
        NEW("新建"),
        REPLICATING("数据复制中"),
        REPLICATED("数据复制完成"),
        CUTTING_OVER("正切中"),
        CUTOVER("正切完成"),
        CUTTING_BACK("回切中"),
        CUTBACK("回切完成");

        private final String description;

        State(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    private String name;
    private String type;
    private String agentInstallationToken;
    private String[] cloudCredentialsIDs;
    private String sourceRegion;
    private String replicationConfiguration;

    /**
     * @apiNote Do not duplicate the name here, store the name at the parent project only.
     */
    @Override
    @DynamoDBIgnore
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgentInstallationToken() {
        return agentInstallationToken;
    }

    public void setAgentInstallationToken(String agentInstallationToken) {
        this.agentInstallationToken = agentInstallationToken;
    }

    @DynamoDBTypeConverted(converter = StringArrayListConverter.class)
    public String[] getCloudCredentialsIDs() {
        return cloudCredentialsIDs;
    }

    public void setCloudCredentialsIDs(String[] cloudCredentialsIDs) {
        this.cloudCredentialsIDs = cloudCredentialsIDs;
    }

    public String getSourceRegion() {
        return sourceRegion;
    }

    public void setSourceRegion(String sourceRegion) {
        this.sourceRegion = sourceRegion;
    }

    public String getReplicationConfiguration() {
        return replicationConfiguration;
    }

    public void setReplicationConfiguration(String replicationConfiguration) {
        this.replicationConfiguration = replicationConfiguration;
    }
}
