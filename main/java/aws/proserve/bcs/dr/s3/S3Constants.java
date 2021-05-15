// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.s3;

public final class S3Constants {

    public static final String PARAM_BUCKET = "/drportal/s3/bucket";
    public static final String COMMON_BUCKET_STACK_NAME = "DRPortal-Bucket";

    public static final String COMMON_VPC_JSON = "template/common-vpc.json";
    public static final String COMMON_BUCKET_JSON = "template/common-bucket.json";

    public static final String LAMBDA_CE = "lambda/ce.zip";
    public static final String LAMBDA_COMMON = "lambda/common.zip";
    public static final String LAMBDA_DBDUMP_MYSQL = "lambda/dbdump/mysql.zip";
    public static final String LAMBDA_DBREPLICA_ORACLE = "lambda/dbreplica/oracle.zip";
    public static final String LAMBDA_DYNAMO = "lambda/dynamo.zip";
    public static final String LAMBDA_S3 = "lambda/s3.zip";
    public static final String LAMBDA_VPC = "lambda/vpc.zip";

}
