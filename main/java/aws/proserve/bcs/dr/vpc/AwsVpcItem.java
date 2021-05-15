// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.vpc;

import aws.proserve.bcs.dr.aws.AwsVpc;
import aws.proserve.bcs.dr.project.ResourceItem;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableAwsVpcItem.class)
@JsonDeserialize(as = ImmutableAwsVpcItem.class)
@Value.Immutable
public interface AwsVpcItem extends ResourceItem<AwsVpc, VpcItem> {

}
