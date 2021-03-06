// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.blob.implementation.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for BlobDeleteType.
 */
public enum BlobDeleteType {
    /**
     * Enum value Permanent.
     */
    PERMANENT("Permanent");

    /**
     * The actual serialized value for a BlobDeleteType instance.
     */
    private final String value;

    BlobDeleteType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a BlobDeleteType instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed BlobDeleteType object, or null if unable to parse.
     */
    @JsonCreator
    public static BlobDeleteType fromString(String value) {
        BlobDeleteType[] items = BlobDeleteType.values();
        for (BlobDeleteType item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
