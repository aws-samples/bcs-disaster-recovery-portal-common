// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.cem;

import aws.proserve.bcs.dr.ce.CloudEndureItem;
import aws.proserve.bcs.dr.dto.HasName;
import aws.proserve.bcs.dr.project.Item;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;

/**
 * One CEM item represents one CloudEndure project.
 */
public class CemItem extends Item implements HasName {

    private String vpcId;
    private CloudEndureItem project;

    @Override
    @DynamoDBIgnore
    public String getName() {
        return project.getName();
    }

    /**
     * @return the VPC ID in the target AWS region.
     */
    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public CloudEndureItem getProject() {
        return project;
    }

    public void setProject(CloudEndureItem project) {
        this.project = project;
    }
}
