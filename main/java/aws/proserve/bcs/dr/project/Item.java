// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.project;

import aws.proserve.bcs.dr.dto.HasState;
import aws.proserve.bcs.dr.dto.Identifiable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

/**
 * An identifiable item where its ID must be unique within its group.
 */
@DynamoDBDocument
public abstract class Item implements Identifiable, HasState {
    private String id;
    private String state;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
