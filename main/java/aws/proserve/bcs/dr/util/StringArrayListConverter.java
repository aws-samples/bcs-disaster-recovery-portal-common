// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.Arrays;
import java.util.List;

public class StringArrayListConverter implements DynamoDBTypeConverter<List<String>, String[]> {

    @Override
    public List<String> convert(String[] object) {
        return Arrays.asList(object);
    }

    @Override
    public String[] unconvert(List<String> object) {
        return object.toArray(new String[0]);
    }
}
