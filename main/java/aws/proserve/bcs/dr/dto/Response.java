// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dto;

import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
public interface Response {
    Response SUCCESS = ImmutableResponse.builder().isSuccessful(true).isKnownIssue(true).build();

    static Response fail(String cause) {
        return response(cause, false);
    }

    static Response unsuccessful(String cause) {
        return response(cause, true);
    }

    static Response response(String cause, boolean known) {
        return ImmutableResponse.builder()
                .isSuccessful(false)
                .isKnownIssue(known)
                .cause(cause).build();
    }

    boolean isSuccessful();

    boolean isKnownIssue();

    @Nullable
    @Value.Default
    default String getCause() {
        return null;
    }
}
