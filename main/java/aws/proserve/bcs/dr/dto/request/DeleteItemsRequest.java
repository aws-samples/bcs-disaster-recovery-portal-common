// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableDeleteItemsRequest.class)
@JsonDeserialize(as = ImmutableDeleteItemsRequest.class)
@Value.Immutable
public interface DeleteItemsRequest {

    String[] getIds();
}
