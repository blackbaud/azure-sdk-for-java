/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.recoveryservices.v2016_06_01;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.microsoft.azure.management.recoveryservices.v2016_06_01.implementation.RecoveryServicesManager;
import com.microsoft.azure.management.recoveryservices.v2016_06_01.implementation.CheckNameAvailabilityResultInner;

/**
 * Type representing CheckNameAvailabilityResult.
 */
public interface CheckNameAvailabilityResult extends HasInner<CheckNameAvailabilityResultInner>, HasManager<RecoveryServicesManager> {
    /**
     * @return the message value.
     */
    String message();

    /**
     * @return the nameAvailable value.
     */
    Boolean nameAvailable();

    /**
     * @return the reason value.
     */
    String reason();

}
