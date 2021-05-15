// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.aws;

import aws.proserve.bcs.dr.dto.HasName;
import aws.proserve.bcs.dr.dto.Identifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableAwsBucket.class)
@JsonDeserialize(as = ImmutableAwsBucket.class)
@Value.Immutable
public interface AwsBucket extends Identifiable, HasName {

    String getRegion();
}
