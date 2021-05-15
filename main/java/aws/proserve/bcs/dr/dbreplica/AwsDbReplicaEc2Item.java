// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dbreplica;

import aws.proserve.bcs.dr.aws.AwsInstance;
import aws.proserve.bcs.dr.project.ResourceItem;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableAwsDbReplicaEc2Item.class)
@JsonDeserialize(as = ImmutableAwsDbReplicaEc2Item.class)
@Value.Immutable
public interface AwsDbReplicaEc2Item extends ResourceItem<AwsInstance, DbReplicaItem> {

}
