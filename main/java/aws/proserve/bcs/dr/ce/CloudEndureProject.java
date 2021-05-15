// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.ce;

import aws.proserve.bcs.dr.project.Side;
import aws.proserve.bcs.dr.project.SubProject;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.annotation.Nullable;
import java.util.List;

/**
 * A DRP CloudEndure project.
 * <p>
 * Such a project manages a cutover and a cutback CloudEndure project, two at maximum. This is to simulate one
 * disaster-recovery project with two migration projects.
 */
public class CloudEndureProject extends SubProject<CloudEndureItem> {

    private boolean publicNetwork;
    private String sourceVpcId;
    private String targetVpcId;
    private String sourceInstanceType;
    private String targetInstanceType;

    @Override
    public List<CloudEndureItem> getItems() {
        return super.getItems();
    }

    @Override
    public void setItems(List<CloudEndureItem> items) {
        super.setItems(items);
    }

    @Nullable
    public CloudEndureItem getItem(Side side) {
        return side == Side.source ? getCutover() : getCutback();
    }

    @Nullable
    @JsonIgnore
    @DynamoDBIgnore
    public CloudEndureItem getCutover() {
        return getItems().isEmpty() ? null : getItems().get(0);
    }

    @JsonIgnore
    @DynamoDBIgnore
    public CloudEndureItem getCutback() {
        return getItems().size() <= 1 ? null : getItems().get(1);
    }

    @DynamoDBTyped(DynamoDBAttributeType.BOOL)
    public boolean isPublicNetwork() {
        return publicNetwork;
    }

    public void setPublicNetwork(boolean publicNetwork) {
        this.publicNetwork = publicNetwork;
    }

    public String getSourceVpcId() {
        return sourceVpcId;
    }

    public void setSourceVpcId(String sourceVpcId) {
        this.sourceVpcId = sourceVpcId;
    }

    public String getTargetVpcId() {
        return targetVpcId;
    }

    public void setTargetVpcId(String targetVpcId) {
        this.targetVpcId = targetVpcId;
    }

    public String getVpcId(Side side) {
        return side == Side.source ? getSourceVpcId() : getTargetVpcId();
    }

    public String getSourceInstanceType() {
        return sourceInstanceType;
    }

    public void setSourceInstanceType(String sourceInstanceType) {
        this.sourceInstanceType = sourceInstanceType;
    }

    public String getTargetInstanceType() {
        return targetInstanceType;
    }

    public void setTargetInstanceType(String targetInstanceType) {
        this.targetInstanceType = targetInstanceType;
    }
}
