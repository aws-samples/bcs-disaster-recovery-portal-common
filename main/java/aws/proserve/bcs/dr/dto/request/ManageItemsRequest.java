// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.dto.request;

import aws.proserve.bcs.dr.project.Item;

public interface ManageItemsRequest<I extends Item> {

    /**
     * @apiNote sub-interface should override this method with reified class.
     */
    I[] getItems();
}
