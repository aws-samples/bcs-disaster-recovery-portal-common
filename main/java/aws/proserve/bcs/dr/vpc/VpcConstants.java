// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.vpc;

public final class VpcConstants {

    public static final String COMMON_VPC_STACK_NAME = "DRPortal-Vpc";
    public static final String COMMON_VPC = COMMON_VPC_STACK_NAME + "/Common";
    public static final String COMMON_PUBLIC_SUBNET1 = COMMON_VPC + "/PublicSubnet1";
    public static final String COMMON_PUBLIC_SUBNET2 = COMMON_VPC + "/PublicSubnet2";
    public static final String COMMON_PRIVATE_SUBNET1 = COMMON_VPC + "/PrivateSubnet1";
    public static final String COMMON_PRIVATE_SUBNET2 = COMMON_VPC + "/PrivateSubnet2";
}
