// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.util;

import javax.annotation.Nullable;

public class Preconditions {
    public static void checkArgument(boolean state, @Nullable Object object) {
        if (!state) {
            throw new IllegalArgumentException(String.valueOf(object));
        }
    }

    public static void checkState(boolean state, @Nullable Object object) {
        if (!state) {
            throw new IllegalStateException(String.valueOf(object));
        }
    }
}
