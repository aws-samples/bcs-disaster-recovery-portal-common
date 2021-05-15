// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.secret;

import aws.proserve.bcs.dr.project.Side;

public final class Secrets {
    private static final String ID_PREFIX = "/drportal/projects/%s/credentials";

    public static String idOfAws(String projectId, Side side) {
        return String.format(ID_PREFIX + "/aws/%s", projectId, side);
    }

    public static String idOfDb(String projectId, Side side, String dbId) {
        return String.format(ID_PREFIX + "/db/%s/%s", projectId, side, dbId);
    }
}
