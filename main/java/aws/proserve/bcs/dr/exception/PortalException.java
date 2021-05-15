// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.exception;

public class PortalException extends RuntimeException {

    public PortalException(String message) {
        super(message);
    }

    public PortalException(String message, Throwable cause) {
        super(message, cause);
    }
}
