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

package com.microsoft.windowsazure.management.sql;

import com.microsoft.windowsazure.core.OperationResponse;
import com.microsoft.windowsazure.core.ServiceOperations;
import com.microsoft.windowsazure.core.pipeline.apache.CustomHttpDelete;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.management.sql.models.ServerChangeAdministratorPasswordParameters;
import com.microsoft.windowsazure.management.sql.models.ServerCreateParameters;
import com.microsoft.windowsazure.management.sql.models.ServerCreateResponse;
import com.microsoft.windowsazure.management.sql.models.ServerListResponse;
import com.microsoft.windowsazure.tracing.CloudTracing;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
* The SQL Database Management API includes operations for managing SQL Database
* servers for a subscription.  (see
* http://msdn.microsoft.com/en-us/library/windowsazure/gg715271.aspx for more
* information)
*/
public class ServerOperationsImpl implements ServiceOperations<SqlManagementClientImpl>, ServerOperations
{
    /**
    * Initializes a new instance of the ServerOperationsImpl class.
    *
    * @param client Reference to the service client.
    */
    ServerOperationsImpl(SqlManagementClientImpl client)
    {
        this.client = client;
    }
    
    private SqlManagementClientImpl client;
    
    /**
    * Gets a reference to the
    * microsoft.windowsazure.management.sql.SqlManagementClientImpl.
    */
    public SqlManagementClientImpl getClient() { return this.client; }
    
    /**
    * Sets the administrative password of a SQL Database server for a
    * subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715272.aspx for
    * more information)
    *
    * @param serverName The server that will have the change made to the
    * administrative user.
    * @param parameters Parameters for the Manage Administrator Password
    * operation.
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<OperationResponse> changeAdministratorPasswordAsync(final String serverName, final ServerChangeAdministratorPasswordParameters parameters)
    {
        return this.getClient().getExecutorService().submit(new Callable<OperationResponse>() { 
            @Override
            public OperationResponse call() throws Exception
            {
                return changeAdministratorPassword(serverName, parameters);
            }
         });
    }
    
    /**
    * Sets the administrative password of a SQL Database server for a
    * subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715272.aspx for
    * more information)
    *
    * @param serverName The server that will have the change made to the
    * administrative user.
    * @param parameters Parameters for the Manage Administrator Password
    * operation.
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public OperationResponse changeAdministratorPassword(String serverName, ServerChangeAdministratorPasswordParameters parameters) throws ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException, UnsupportedEncodingException, IOException, ServiceException
    {
        // Validate
        if (serverName == null)
        {
            throw new NullPointerException("serverName");
        }
        if (parameters == null)
        {
            throw new NullPointerException("parameters");
        }
        if (parameters.getNewPassword() == null)
        {
            throw new NullPointerException("parameters.NewPassword");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace)
        {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("serverName", serverName);
            tracingParameters.put("parameters", parameters);
            CloudTracing.enter(invocationId, this, "changeAdministratorPasswordAsync", tracingParameters);
        }
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/sqlservers/servers/" + serverName + "?op=ResetPassword";
        
        // Create HTTP transport objects
        HttpPost httpRequest = new HttpPost(url);
        
        // Set Headers
        httpRequest.setHeader("Content-Type", "application/xml");
        httpRequest.setHeader("x-ms-version", "2012-03-01");
        
        // Serialize Request
        String requestContent = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document requestDoc = documentBuilder.newDocument();
        
        Element administratorLoginPasswordElement = requestDoc.createElementNS("http://schemas.microsoft.com/sqlazure/2010/12/", "AdministratorLoginPassword");
        requestDoc.appendChild(administratorLoginPasswordElement);
        
        administratorLoginPasswordElement.appendChild(requestDoc.createTextNode(parameters.getNewPassword()));
        
        DOMSource domSource = new DOMSource(requestDoc);
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(domSource, streamResult);
        requestContent = stringWriter.toString();
        StringEntity entity = new StringEntity(requestContent);
        httpRequest.setEntity(entity);
        httpRequest.setHeader("Content-Type", "application/xml");
        
        // Send Request
        HttpResponse httpResponse = null;
        if (shouldTrace)
        {
            CloudTracing.sendRequest(invocationId, httpRequest);
        }
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        if (shouldTrace)
        {
            CloudTracing.receiveResponse(invocationId, httpResponse);
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, requestContent, httpResponse, httpResponse.getEntity());
            if (shouldTrace)
            {
                CloudTracing.error(invocationId, ex);
            }
            throw ex;
        }
        
        // Create Result
        OperationResponse result = null;
        result = new OperationResponse();
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        if (shouldTrace)
        {
            CloudTracing.exit(invocationId, result);
        }
        return result;
    }
    
    /**
    * Adds a new SQL Database server to a subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715274.aspx for
    * more information)
    *
    * @param parameters Parameters supplied to the Create Server operation.
    * @return The response returned from the Create Server operation.
    */
    @Override
    public Future<ServerCreateResponse> createAsync(final ServerCreateParameters parameters)
    {
        return this.getClient().getExecutorService().submit(new Callable<ServerCreateResponse>() { 
            @Override
            public ServerCreateResponse call() throws Exception
            {
                return create(parameters);
            }
         });
    }
    
    /**
    * Adds a new SQL Database server to a subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715274.aspx for
    * more information)
    *
    * @param parameters Parameters supplied to the Create Server operation.
    * @return The response returned from the Create Server operation.
    */
    @Override
    public ServerCreateResponse create(ServerCreateParameters parameters) throws ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException, UnsupportedEncodingException, IOException, ServiceException, ParseException
    {
        // Validate
        if (parameters == null)
        {
            throw new NullPointerException("parameters");
        }
        if (parameters.getAdministratorPassword() == null)
        {
            throw new NullPointerException("parameters.AdministratorPassword");
        }
        if (parameters.getAdministratorUserName() == null)
        {
            throw new NullPointerException("parameters.AdministratorUserName");
        }
        if (parameters.getLocation() == null)
        {
            throw new NullPointerException("parameters.Location");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace)
        {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("parameters", parameters);
            CloudTracing.enter(invocationId, this, "createAsync", tracingParameters);
        }
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/sqlservers/servers";
        
        // Create HTTP transport objects
        HttpPost httpRequest = new HttpPost(url);
        
        // Set Headers
        httpRequest.setHeader("Content-Type", "application/xml");
        httpRequest.setHeader("x-ms-version", "2012-03-01");
        
        // Serialize Request
        String requestContent = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document requestDoc = documentBuilder.newDocument();
        
        Element serverElement = requestDoc.createElementNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Server");
        requestDoc.appendChild(serverElement);
        
        Element administratorLoginElement = requestDoc.createElementNS("http://schemas.microsoft.com/sqlazure/2010/12/", "AdministratorLogin");
        administratorLoginElement.appendChild(requestDoc.createTextNode(parameters.getAdministratorUserName()));
        serverElement.appendChild(administratorLoginElement);
        
        Element administratorLoginPasswordElement = requestDoc.createElementNS("http://schemas.microsoft.com/sqlazure/2010/12/", "AdministratorLoginPassword");
        administratorLoginPasswordElement.appendChild(requestDoc.createTextNode(parameters.getAdministratorPassword()));
        serverElement.appendChild(administratorLoginPasswordElement);
        
        Element locationElement = requestDoc.createElementNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Location");
        locationElement.appendChild(requestDoc.createTextNode(parameters.getLocation()));
        serverElement.appendChild(locationElement);
        
        DOMSource domSource = new DOMSource(requestDoc);
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(domSource, streamResult);
        requestContent = stringWriter.toString();
        StringEntity entity = new StringEntity(requestContent);
        httpRequest.setEntity(entity);
        httpRequest.setHeader("Content-Type", "application/xml");
        
        // Send Request
        HttpResponse httpResponse = null;
        if (shouldTrace)
        {
            CloudTracing.sendRequest(invocationId, httpRequest);
        }
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        if (shouldTrace)
        {
            CloudTracing.receiveResponse(invocationId, httpResponse);
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 201)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, requestContent, httpResponse, httpResponse.getEntity());
            if (shouldTrace)
            {
                CloudTracing.error(invocationId, ex);
            }
            throw ex;
        }
        
        // Create Result
        ServerCreateResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new ServerCreateResponse();
        DocumentBuilderFactory documentBuilderFactory2 = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder2 = documentBuilderFactory2.newDocumentBuilder();
        Document responseDoc = documentBuilder2.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "ServerName");
        Element serverNameElement = elements.getLength() > 0 ? ((Element)elements.item(0)) : null;
        if (serverNameElement != null)
        {
            result.setServerName(serverNameElement.getTextContent());
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        if (shouldTrace)
        {
            CloudTracing.exit(invocationId, result);
        }
        return result;
    }
    
    /**
    * Drops a SQL Database server from a subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715285.aspx for
    * more information)
    *
    * @param serverName The name of the server to be deleted.
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<OperationResponse> deleteAsync(final String serverName)
    {
        return this.getClient().getExecutorService().submit(new Callable<OperationResponse>() { 
            @Override
            public OperationResponse call() throws Exception
            {
                return delete(serverName);
            }
         });
    }
    
    /**
    * Drops a SQL Database server from a subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715285.aspx for
    * more information)
    *
    * @param serverName The name of the server to be deleted.
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public OperationResponse delete(String serverName) throws IOException, ServiceException
    {
        // Validate
        if (serverName == null)
        {
            throw new NullPointerException("serverName");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace)
        {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("serverName", serverName);
            CloudTracing.enter(invocationId, this, "deleteAsync", tracingParameters);
        }
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/sqlservers/servers/" + serverName;
        
        // Create HTTP transport objects
        CustomHttpDelete httpRequest = new CustomHttpDelete(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2012-03-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        if (shouldTrace)
        {
            CloudTracing.sendRequest(invocationId, httpRequest);
        }
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        if (shouldTrace)
        {
            CloudTracing.receiveResponse(invocationId, httpResponse);
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            if (shouldTrace)
            {
                CloudTracing.error(invocationId, ex);
            }
            throw ex;
        }
        
        // Create Result
        OperationResponse result = null;
        result = new OperationResponse();
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        if (shouldTrace)
        {
            CloudTracing.exit(invocationId, result);
        }
        return result;
    }
    
    /**
    * Returns all SQL Database servers that are provisioned for a subscription.
    * (see http://msdn.microsoft.com/en-us/library/windowsazure/gg715269.aspx
    * for more information)
    *
    * @return The response structure for the Server List operation.
    */
    @Override
    public Future<ServerListResponse> listAsync()
    {
        return this.getClient().getExecutorService().submit(new Callable<ServerListResponse>() { 
            @Override
            public ServerListResponse call() throws Exception
            {
                return list();
            }
         });
    }
    
    /**
    * Returns all SQL Database servers that are provisioned for a subscription.
    * (see http://msdn.microsoft.com/en-us/library/windowsazure/gg715269.aspx
    * for more information)
    *
    * @return The response structure for the Server List operation.
    */
    @Override
    public ServerListResponse list() throws IOException, ServiceException, ParserConfigurationException, SAXException, ParseException
    {
        // Validate
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace)
        {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            CloudTracing.enter(invocationId, this, "listAsync", tracingParameters);
        }
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/sqlservers/servers";
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2012-03-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        if (shouldTrace)
        {
            CloudTracing.sendRequest(invocationId, httpRequest);
        }
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        if (shouldTrace)
        {
            CloudTracing.receiveResponse(invocationId, httpResponse);
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            if (shouldTrace)
            {
                CloudTracing.error(invocationId, ex);
            }
            throw ex;
        }
        
        // Create Result
        ServerListResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new ServerListResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Servers");
        Element serversSequenceElement = elements.getLength() > 0 ? ((Element)elements.item(0)) : null;
        if (serversSequenceElement != null)
        {
            for (int i1 = 0; i1 < serversSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Server").getLength(); i1 = i1 + 1)
            {
                org.w3c.dom.Element serversElement = ((org.w3c.dom.Element)serversSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Server").item(i1));
                ServerListResponse.Server serverInstance = new ServerListResponse.Server();
                result.getServers().add(serverInstance);
                
                NodeList elements2 = serversElement.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Name");
                Element nameElement = elements2.getLength() > 0 ? ((Element)elements2.item(0)) : null;
                if (nameElement != null)
                {
                    String nameInstance;
                    nameInstance = nameElement.getTextContent();
                    serverInstance.setName(nameInstance);
                }
                
                NodeList elements3 = serversElement.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "AdministratorLogin");
                Element administratorLoginElement = elements3.getLength() > 0 ? ((Element)elements3.item(0)) : null;
                if (administratorLoginElement != null)
                {
                    String administratorLoginInstance;
                    administratorLoginInstance = administratorLoginElement.getTextContent();
                    serverInstance.setAdministratorUserName(administratorLoginInstance);
                }
                
                NodeList elements4 = serversElement.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Location");
                Element locationElement = elements4.getLength() > 0 ? ((Element)elements4.item(0)) : null;
                if (locationElement != null)
                {
                    String locationInstance;
                    locationInstance = locationElement.getTextContent();
                    serverInstance.setLocation(locationInstance);
                }
                
                NodeList elements5 = serversElement.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Features");
                Element featuresSequenceElement = elements5.getLength() > 0 ? ((Element)elements5.item(0)) : null;
                if (featuresSequenceElement != null)
                {
                    for (int i2 = 0; i2 < featuresSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Feature").getLength(); i2 = i2 + 1)
                    {
                        org.w3c.dom.Element featuresElement = ((org.w3c.dom.Element)featuresSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Feature").item(i2));
                        NodeList elements6 = featuresElement.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Name");
                        String featuresKey = elements6.getLength() > 0 ? ((org.w3c.dom.Element)elements6.item(0)).getTextContent() : null;
                        NodeList elements7 = featuresElement.getElementsByTagNameNS("http://schemas.microsoft.com/sqlazure/2010/12/", "Value");
                        String featuresValue = elements7.getLength() > 0 ? ((org.w3c.dom.Element)elements7.item(0)).getTextContent() : null;
                        serverInstance.getFeatures().put(featuresKey, featuresValue);
                    }
                }
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        if (shouldTrace)
        {
            CloudTracing.exit(invocationId, result);
        }
        return result;
    }
}
