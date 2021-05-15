// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.project;

import aws.proserve.bcs.dr.dto.Identifiable;
import org.immutables.value.Value;

import javax.annotation.Nullable;

/**
 * The other resource(s) <b>and</b> the corresponding DRPortal item.
 *
 * @param <A> the AWS resource class.
 * @param <I> the DRPortal item class.
 */
public interface ResourceItem<A extends Identifiable, I extends Item> extends Identifiable {
    /**
     * @apiNote redundant, usually the ID of the item.
     */
    @Override
    @Value.Default
    default String getId() {
        return getItem().getId();
    }

    I getItem();

    A getSource();

    /**
     * @apiNote For vpc replication, the target vpc is unknown when creating this object.
     */
    @Nullable
    A getTarget();

    /**
     * @return the source region as defined in the parent project of this item.
     */
    String getSourceRegion();

    /**
     * @return the target region as defined in the parent project of this item.
     */
    String getTargetRegion();
}
