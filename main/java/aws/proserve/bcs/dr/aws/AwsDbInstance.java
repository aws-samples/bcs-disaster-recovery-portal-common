// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.aws;

import aws.proserve.bcs.dr.dto.HasName;
import aws.proserve.bcs.dr.dto.Identifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableAwsDbInstance.class)
@JsonDeserialize(as = ImmutableAwsDbInstance.class)
@Value.Immutable
public interface AwsDbInstance extends Identifiable, HasName {

    @Override
    @Value.Default
    default String getId() {
        return getDBInstanceIdentifier();
    }

    boolean getMultiAZ();

    String getDBInstanceIdentifier();

    String getEngine();

    String getEngineVersion();

    String getInstanceClass();

    String getInstanceStatus();

    AwsDbEndpoint getEndpoint();

    String getMasterUsername();

    String[] getSubnetIds();

    String[] getSecurityGroupIds();
}
