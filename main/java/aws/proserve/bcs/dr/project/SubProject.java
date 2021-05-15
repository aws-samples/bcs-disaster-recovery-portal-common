// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.project;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import java.util.List;

@DynamoDBDocument
public class SubProject<I extends Item> {

    private List<I> items;

    public List<I> getItems() {
        return items;
    }

    public void setItems(List<I> items) {
        this.items = items;
    }

    public I find(String id) {
        return items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unable to find item [" + id + "]"));
    }
}
