// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.s3;

import aws.proserve.bcs.dr.aws.AwsBucket;
import aws.proserve.bcs.dr.project.ResourceItem;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableAwsBucketItem.class)
@JsonDeserialize(as = ImmutableAwsBucketItem.class)
@Value.Immutable
public interface AwsBucketItem extends ResourceItem<AwsBucket, S3Item> {

}
