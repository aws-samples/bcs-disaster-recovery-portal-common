// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.vpc;

import com.amazonaws.services.ec2.model.Filter;

import java.util.List;

public class Filters {

    public static Filter name(String value) {
        return tag("Name", value);
    }

    public static Filter tag(String key) {
        return new Filter("tag-key", List.of(key));
    }

    public static Filter tag(String key, String value) {
        return new Filter("tag:" + key, List.of(value));
    }

    public static Filter instanceId(String vpcId) {
        return new Filter("instance-id", List.of(vpcId));
    }

    public static Filter vpcId(String vpcId) {
        return new Filter("vpc-id", List.of(vpcId));
    }

    public static Filter accepterVpcId(String vpcId) {
        return new Filter("accepter-vpc-info.vpc-id", List.of(vpcId));
    }

    public static Filter associatedMain() {
        return new Filter("association.main", List.of("true"));
    }

    public static Filter associatedSubnetId(String subnetId) {
        return new Filter("association.subnet-id", List.of(subnetId));
    }

    public static Filter attachedVpcId(String vpcId) {
        return new Filter("attachment.vpc-id", List.of(vpcId));
    }

    public static Filter statusCode(String statusCode) {
        return new Filter("status-code", List.of(statusCode));
    }
}
