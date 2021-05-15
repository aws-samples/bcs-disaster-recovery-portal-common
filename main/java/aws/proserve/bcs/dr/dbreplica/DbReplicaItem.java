// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dbreplica;

import aws.proserve.bcs.dr.project.PairedItem;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;

public class DbReplicaItem extends PairedItem {

    @DynamoDBIgnore
    public String getStateDescription() {
        return State.valueOf(getState()).getDescription();
    }

    public enum State {
        NEW("新建"),
        REPLICATING("复制中"),
        SWITCHOVER("切换"),
        FAILOVER("恢复");

        private final String description;

        State(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
