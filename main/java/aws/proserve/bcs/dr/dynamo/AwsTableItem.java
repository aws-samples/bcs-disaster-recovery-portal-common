// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dynamo;

import aws.proserve.bcs.dr.aws.AwsTable;
import aws.proserve.bcs.dr.project.ResourceItem;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableAwsTableItem.class)
@JsonDeserialize(as = ImmutableAwsTableItem.class)
@Value.Immutable
public interface AwsTableItem extends ResourceItem<AwsTable, DynamoItem> {

}
