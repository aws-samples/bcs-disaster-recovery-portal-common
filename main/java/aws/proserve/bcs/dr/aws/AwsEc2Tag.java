// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.aws;

import com.amazonaws.services.ec2.model.Tag;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;
import java.util.stream.Collectors;

@JsonSerialize(as = ImmutableAwsEc2Tag.class)
@JsonDeserialize(as = ImmutableAwsEc2Tag.class)
@Value.Immutable
public interface AwsEc2Tag {

    static List<AwsEc2Tag> convert(List<Tag> tags) {
        return tags.stream()
                .map(t -> ImmutableAwsEc2Tag.builder()
                        .key(t.getKey())
                        .value(t.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    static String getName(List<AwsEc2Tag> tags) {
        return tags == null ? null : tags.stream()
                .filter(t -> t.getKey().equals("Name"))
                .findFirst()
                .map(AwsEc2Tag::getValue)
                .orElse(null);
    }

    String getKey();

    String getValue();
}
