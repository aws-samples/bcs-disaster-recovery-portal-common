// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.project;

import aws.proserve.bcs.dr.dto.HasName;

import java.util.stream.Stream;

public enum Component implements HasName {
    Boot("boot"),
    CloudEndure("ce"),
    CloudEndureManager("ce.manager"),
    DynamoDB("dynamo"),
    DbDumpMySql("db.dump.mysql"),
    DbDumpOracle("db.dump.oracle"),
    DbReplicaOracleEc2("db.replica.oracle.ec2"),
    S3("s3"),
    VPC("vpc");

    public static Component of(String value) {
        return Stream.of(values())
                .filter(v -> v.getName().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(value));
    }

    private final String name;

    Component(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
