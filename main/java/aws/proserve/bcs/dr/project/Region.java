// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.project;

import aws.proserve.bcs.dr.dto.HasName;
import aws.proserve.bcs.dr.dto.Identifiable;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;

@DynamoDBDocument
public class Region implements Identifiable, HasName {

    private String name;
    private String description;

    public Region() {
    }

    public Region(Regions regions) {
        this.name = regions.getName();
        this.description = regions.getDescription();
    }

    /**
     * @apiNote Duplicated value of name. Must not use @JsonIgnore as GWT needs it.
     */
    @Override
    @DynamoDBIgnore
    public String getId() {
        return getName();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public Regions toAwsRegion() {
        return Regions.fromName(name);
    }
}
