/**
 * 
 * Copyright (c) Microsoft and contributors.  All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management.servicebus.models;

import com.microsoft.windowsazure.core.OperationResponse;
import java.util.ArrayList;
import java.util.Iterator;

/**
* A standard storage response including an HTTP status code and request ID.
*/
public class ServiceBusNotificationHubsResponse extends OperationResponse implements Iterable<ServiceBusNotificationHub>
{
    private ArrayList<ServiceBusNotificationHub> notificationHubs;
    
    public ArrayList<ServiceBusNotificationHub> getNotificationHubs() { return this.notificationHubs; }
    
    public void setNotificationHubs(ArrayList<ServiceBusNotificationHub> notificationHubs) { this.notificationHubs = notificationHubs; }
    
    /**
    * Initializes a new instance of the ServiceBusNotificationHubsResponse
    * class.
    *
    */
    public ServiceBusNotificationHubsResponse()
    {
        this.notificationHubs = new ArrayList<ServiceBusNotificationHub>();
    }
    
    /**
    * Gets the sequence of NotificationHubs.
    *
    */
    public Iterator<ServiceBusNotificationHub> iterator()
    {
        return this.getNotificationHubs().iterator();
    }
}
