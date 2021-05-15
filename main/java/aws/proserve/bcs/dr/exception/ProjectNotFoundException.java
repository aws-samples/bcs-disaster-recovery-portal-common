// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.exception;

public class ProjectNotFoundException extends PortalException {

    public ProjectNotFoundException(String projectId) {
        super(projectId);
    }
}
