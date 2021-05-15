// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dbdump;

import aws.proserve.bcs.dr.aws.AwsDbInstance;
import aws.proserve.bcs.dr.project.ResourceItem;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableAwsDbDumpItem.class)
@JsonDeserialize(as = ImmutableAwsDbDumpItem.class)
@Value.Immutable
public interface AwsDbDumpItem extends ResourceItem<AwsDbInstance, DbDumpItem> {

}
