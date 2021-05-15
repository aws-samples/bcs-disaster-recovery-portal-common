// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.project;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class PairedItem extends TimedItem {

    private String source;
    private String target;

    @Override
    @JsonIgnore
    @DynamoDBIgnore
    public String getId() {
        return source + "<>" + target;
    }

    public String get(Side side) {
        return side == Side.source ? source : target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
