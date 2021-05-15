// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dbreplica;

import aws.proserve.bcs.dr.project.ResourceItem;
import aws.proserve.bcs.dr.aws.AwsDbInstance;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableAwsDbReplicaItem.class)
@JsonDeserialize(as = ImmutableAwsDbReplicaItem.class)
@Value.Immutable
public interface AwsDbReplicaItem extends ResourceItem<AwsDbInstance, DbReplicaItem> {

}
