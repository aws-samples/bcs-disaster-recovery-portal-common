// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.aws;

import aws.proserve.bcs.dr.dto.HasName;
import aws.proserve.bcs.dr.dto.HasState;
import aws.proserve.bcs.dr.dto.Identifiable;
import com.amazonaws.services.ec2.model.GroupIdentifier;
import com.amazonaws.services.ec2.model.Instance;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

@JsonSerialize(as = ImmutableAwsInstance.class)
@JsonDeserialize(as = ImmutableAwsInstance.class)
@Value.Immutable
public interface AwsInstance extends Identifiable, HasName, HasState {

    static AwsInstance convert(Instance instance) {
        return ImmutableAwsInstance.builder()
                .instanceId(instance.getInstanceId())
                .instanceType(instance.getInstanceType())
                .privateIpAddress(instance.getPrivateIpAddress())
                .publicIpAddress(instance.getPublicIpAddress())
                .securityGroups(instance.getSecurityGroups().stream().map(GroupIdentifier::getGroupId).collect(Collectors.toList()))
                .state(instance.getState().getName())
                .subnetId(instance.getSubnetId())
                .tags(AwsEc2Tag.convert(instance.getTags()))
                .vpcId(instance.getVpcId())
                .build();
    }

    @Override
    @Value.Default
    default String getId() {
        return getInstanceId();
    }

    @Override
    @Nullable
    @Value.Default
    default String getName() {
        return AwsEc2Tag.getName(getTags());
    }

    String getInstanceId();

    String getInstanceType();

    @Nullable
    String getPublicIpAddress();

    String getPrivateIpAddress();

    List<String> getSecurityGroups();

    String getSubnetId();

    @Nullable
    String getVpcId();

    List<AwsEc2Tag> getTags();

    /**
     * @apiNote added by DRPortal
     */
    @Nullable
    @Value.Default
    default String getRegion() {
        return null;
    }
}
