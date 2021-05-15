// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dto.request;

import aws.proserve.bcs.dr.dto.HasName;

public interface CreateProjectRequest extends HasName {

    String getSourceRegion();

    String getTargetRegion();
}
