// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.vpc;

import aws.proserve.bcs.dr.project.PairedItem;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is to be stored in DynamoDB with only VPC ID for reduced information.
 */
public class VpcItem extends PairedItem {

    private boolean continuous;
    private String cidr;

    @Override
    @JsonIgnore
    @DynamoDBIgnore
    public String getId() {
        return getSource();
    }

    @DynamoDBTyped(DynamoDBAttributeType.BOOL)
    public boolean isContinuous() {
        return continuous;
    }

    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    @DynamoDBIgnore
    public String getStateDescription() {
        return State.valueOf(getState()).getDescription();
    }

    public enum State {
        NEW("新建"),
        REPLICATING("复制中"),
        REPLICATED("复制完成"),
        FAILED("复制失败");

        private final String description;

        State(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
