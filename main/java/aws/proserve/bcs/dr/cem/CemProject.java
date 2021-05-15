// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.cem;

import aws.proserve.bcs.dr.ce.CloudEndureItem;
import aws.proserve.bcs.dr.project.SubProject;
import aws.proserve.bcs.dr.util.Preconditions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;

import java.util.List;

/**
 * A DRP CloudEndure manager project.
 * <p>
 * Such a project manages exactly one CloudEndure project, in line with DRP CloudEndure project.
 */
public class CemProject extends SubProject<CemItem> {

    @Override
    public List<CemItem> getItems() {
        return super.getItems();
    }

    @Override
    public void setItems(List<CemItem> items) {
        super.setItems(items);
    }

    @DynamoDBIgnore
    public CemItem getFirst() {
        Preconditions.checkArgument(getItems() != null && !getItems().isEmpty(), "Project is empty");
        return getItems().get(0);
    }

    @DynamoDBIgnore
    public CloudEndureItem getProject() {
        return getFirst().getProject();
    }
}
