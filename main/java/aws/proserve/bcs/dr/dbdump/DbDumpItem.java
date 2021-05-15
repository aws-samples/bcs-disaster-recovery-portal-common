// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dbdump;

import aws.proserve.bcs.dr.project.PairedItem;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;

public class DbDumpItem extends PairedItem {

    private String dumpExecutionArn;
    private String restoreExecutionArn;

    @DynamoDBIgnore
    public String getStateDescription() {
        return State.valueOf(getState()).getDescription();
    }

    public String getDumpExecutionArn() {
        return dumpExecutionArn;
    }

    public void setDumpExecutionArn(String dumpExecutionArn) {
        this.dumpExecutionArn = dumpExecutionArn;
    }

    public String getRestoreExecutionArn() {
        return restoreExecutionArn;
    }

    public void setRestoreExecutionArn(String restoreExecutionArn) {
        this.restoreExecutionArn = restoreExecutionArn;
    }

    public enum State {
        NEW("新建"),
        DUMPING("转存中"),
        RESTORING("载入中"),
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
