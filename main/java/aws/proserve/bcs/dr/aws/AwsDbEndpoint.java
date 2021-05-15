// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.aws;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableAwsDbEndpoint.class)
@JsonDeserialize(as = ImmutableAwsDbEndpoint.class)
@Value.Immutable
public interface AwsDbEndpoint {

    String getAddress();

    int getPort();
}
