// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.vpc;

import aws.proserve.bcs.dr.project.SubProject;

import java.util.List;

public class VpcProject extends SubProject<VpcItem> {

    /**
     * @apiNote need to override to pass the reified type information for DynamoDB converter.
     */
    @Override
    public List<VpcItem> getItems() {
        return super.getItems();
    }

    @Override
    public void setItems(List<VpcItem> items) {
        super.setItems(items);
    }
}
