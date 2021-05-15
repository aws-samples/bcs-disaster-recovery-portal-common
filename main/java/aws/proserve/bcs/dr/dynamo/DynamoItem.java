// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dynamo;

import aws.proserve.bcs.dr.project.PairedItem;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;

public class DynamoItem extends PairedItem {

    private String executionArn;

    @DynamoDBIgnore
    public String getStateDescription() {
        return State.valueOf(getState()).getDescription();
    }

    public String getExecutionArn() {
        return executionArn;
    }

    public void setExecutionArn(String executionArn) {
        this.executionArn = executionArn;
    }

    public enum State {
        NEW("新建"),
        REPLICATING("复制中"),
        STOPPED("复制停止"),
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
