// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.aws;

import aws.proserve.bcs.dr.dto.HasName;
import aws.proserve.bcs.dr.dto.HasState;
import aws.proserve.bcs.dr.dto.Identifiable;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeVpcsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.Vpc;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize(as = ImmutableAwsVpc.class)
@JsonDeserialize(as = ImmutableAwsVpc.class)
@Value.Immutable
public interface AwsVpc extends Identifiable, HasName, HasState {

    static AwsVpc convert(Vpc vpc) {
        return ImmutableAwsVpc.builder()
                .vpcId(vpc.getVpcId())
                .cidr(vpc.getCidrBlock())
                .isDefault(vpc.isDefault())
                .state(vpc.getState())
                .tags(AwsEc2Tag.convert(vpc.getTags()))
                .build();
    }

    static List<Vpc> getVpcs(AmazonEC2 ec2) {
        final var describeRequest = new DescribeVpcsRequest();
        final var vpcs = new ArrayList<Vpc>();
        DescribeVpcsResult result;
        do {
            result = ec2.describeVpcs(describeRequest);
            describeRequest.setNextToken(result.getNextToken());
            vpcs.addAll(result.getVpcs());
        } while (result.getNextToken() != null);
        return vpcs;
    }

    @Override
    @Value.Default
    default String getId() {
        return getVpcId();
    }

    String getVpcId();

    @Override
    @Nullable
    @Value.Default
    default String getName() {
        return AwsEc2Tag.getName(getTags());
    }

    @Value.Default
    default String getCidr() {
        return "";
    }

    @Value.Default
    default boolean getIsDefault() {
        return false;
    }

    List<AwsEc2Tag> getTags();

    /**
     * @apiNote added by DRPortal
     */
    @Nullable
    @Value.Default
    default String getPeerVpcId() {
        return null;
    }
}
