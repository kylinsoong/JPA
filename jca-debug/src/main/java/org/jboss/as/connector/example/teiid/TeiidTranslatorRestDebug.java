package org.jboss.as.connector.example.teiid;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.activation.DataSource;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Binding;
import javax.xml.ws.Dispatch;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.Response;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.http.HTTPConduitFactory;
import org.apache.cxf.transport.http.asyncclient.AsyncHTTPConduitFactory;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.ietf.jgss.GSSCredential;
import org.teiid.OAuthCredential;
import org.teiid.jboss.oauth.OAuth10CredentialImpl;
import org.teiid.translator.WSConnection;

public class TeiidTranslatorRestDebug {
    
    static {
        System.setProperty("http.proxyHost", "squid.apac.redhat.com");
        System.setProperty("http.proxyPort", "3128");
    }
    
    private static final String CONNECTION_TIMEOUT = "javax.xml.ws.client.connectionTimeout"; //$NON-NLS-1$
    private static final String RECEIVE_TIMEOUT = "javax.xml.ws.client.receiveTimeout"; //$NON-NLS-1$

    public static void main(String[] args) throws IOException {
        
        int responseCode = 200;
        boolean useResponseContext = false;
        Map<String, Object> responseContext = Collections.emptyMap();

        String method = "GET";
        String endpoint = "https://api.twitter.com/1.1/statuses/user_timeline.json";
        
        Dispatch<DataSource> dispatch = createDispatch(HTTPBinding.HTTP_BINDING, endpoint, DataSource.class, Mode.MESSAGE);
        
        dispatch.getRequestContext().put(MessageContext.HTTP_REQUEST_METHOD, method);
        
        Map<String, List<String>> httpHeaders = (Map<String, List<String>>)dispatch.getRequestContext().get(MessageContext.HTTP_REQUEST_HEADERS);
        dispatch.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, httpHeaders);
        
        DataSource returnValue = dispatch.invoke(null);
        
        Map<String, Object> rc = dispatch.getResponseContext();
        responseCode = (Integer)rc.get(WSConnection.STATUS_CODE);
        if (useResponseContext) {
            //it's presumed that the caller will handle the response codes
            responseContext = rc;
        } else {
            //TODO: may need to add logic around some 200/300 codes - cxf should at least be logging this
            if (responseCode >= 400) {
                
                throw new RuntimeException("Rest invoke error");
            }
        }
        
        // print return value
        System.out.println("=========== Return Value ===========");
        System.out.println("Name: " + returnValue.getName());
        System.out.println("ContentType: " + returnValue.getContentType());
        System.out.println("Content:");
        System.out.println(convertStreamToString(returnValue.getInputStream()));
    }

    private static String convertStreamToString(InputStream inputStream) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer);
        return writer.toString();
    }

    private static <T> Dispatch<T>  createDispatch(String binding, String endpoint, Class<T> type, Mode mode) {
        
        if (endpoint != null) {
            try {
                new URL(endpoint);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        
        Dispatch<T> dispatch = null;
        
        if (HTTPBinding.HTTP_BINDING.equals(binding) && (type == DataSource.class)) {
            Bus bus = BusFactory.getThreadDefaultBus();
            BusFactory.setThreadDefaultBus(null);
            try {
                dispatch = (Dispatch<T>) new HttpDispatch(endpoint, null, "teiid");
            } finally {
                BusFactory.setThreadDefaultBus(bus);
            }
        }
        
        OAuth10CredentialImpl credential = new OAuth10CredentialImpl();
        credential.setConsumerKey("nHhsL5Dm5Zi77eqWDDcQ");
        credential.setConsumerSecret("VovoE7R5RJOI9GSAXTx25yppfDJ6y5SUKPy2vlrVCDM");
        credential.setAccessToken("209490245-cUtcom3nEnV53mYZfhWegZpnm90nmPds0icguwzL");
        credential.setAccessSecret("WKIYjXmplrdssy382iJnoNzd0vVDCa13nCWywKzKrE");
        dispatch.getRequestContext().put(OAuthCredential.class.getName(), credential);
        
        if (HTTPBinding.HTTP_BINDING.equals(binding)) {
            Map<String, List<String>> httpHeaders = (Map<String, List<String>>)dispatch.getRequestContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            if(httpHeaders == null) {
                httpHeaders = new HashMap<String, List<String>>();
            }
            httpHeaders.put("Content-Type", Collections.singletonList("text/xml; charset=utf-8"));
            httpHeaders.put("User-Agent", Collections.singletonList("Teiid Server"));
            dispatch.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, httpHeaders);
        }
        
        return dispatch;
    }
    
    private static final class HttpDispatch implements Dispatch<DataSource> {

        private static final String AUTHORIZATION = "Authorization"; //$NON-NLS-1$
        private HashMap<String, Object> requestContext = new HashMap<String, Object>();
        private HashMap<String, Object> responseContext = new HashMap<String, Object>();
        private WebClient client;
        private String endpoint;
        private String configFile;

        public HttpDispatch(String endpoint, String configFile, @SuppressWarnings("unused") String configName) {
            this.endpoint = endpoint;
            this.configFile = configFile;
        }

        WebClient createWebClient(String baseAddress, Bus bus) {
            JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
            bean.setBus(bus);
            bean.setAddress(baseAddress);
            return bean.createWebClient();
        }   
        
        Bus getBus(String configLocation) {
            if (configLocation != null) {
                SpringBusFactory bf = new SpringBusFactory();
                return bf.createBus(configLocation);
            } else {
                return BusFactory.getThreadDefaultBus();
            }
        }           
        
        @Override
        public DataSource invoke(DataSource msg) {
            try {
                final URL url = new URL(this.endpoint);
                final String httpMethod = (String)this.requestContext.get(MessageContext.HTTP_REQUEST_METHOD);

                // see to use patch
                // http://stackoverflow.com/questions/32067687/how-to-use-patch-method-in-cxf
                Bus bus = getBus(this.configFile);
                if (httpMethod.equals("PATCH")) {
                    bus.setProperty("use.async.http.conduit", Boolean.TRUE);
                    bus.setExtension(new AsyncHTTPConduitFactory(bus), HTTPConduitFactory.class);
                }
                this.client = createWebClient(this.endpoint, bus);
                
                Map<String, List<String>> header = (Map<String, List<String>>)this.requestContext.get(MessageContext.HTTP_REQUEST_HEADERS);
                for (Map.Entry<String, List<String>> entry : header.entrySet()) {
                    this.client.header(entry.getKey(), entry.getValue().toArray());
                }
                
                if (this.requestContext.get(AuthorizationPolicy.class.getName()) != null) {
                    HTTPConduit conduit = (HTTPConduit)WebClient.getConfig(this.client).getConduit();
                    AuthorizationPolicy policy = (AuthorizationPolicy)this.requestContext.get(AuthorizationPolicy.class.getName());
                    conduit.setAuthorization(policy);
                }
                else if (this.requestContext.get(GSSCredential.class.getName()) != null) {
                    WebClient.getConfig(this.client).getRequestContext().put(GSSCredential.class.getName(), this.requestContext.get(GSSCredential.class.getName()));
                    WebClient.getConfig(this.client).getRequestContext().put("auth.spnego.requireCredDelegation", true); //$NON-NLS-1$ 
                }
                else if (this.requestContext.get(OAuthCredential.class.getName()) != null) {
                    OAuthCredential credential = (OAuthCredential)this.requestContext.get(OAuthCredential.class.getName());                    
                    this.client.header(AUTHORIZATION, credential.getAuthorizationHeader(this.endpoint, httpMethod));
                }
                
                InputStream payload = null;
                if (msg != null) {
                    payload = msg.getInputStream();
                }
                
                HTTPClientPolicy clientPolicy = WebClient.getConfig(this.client).getHttpConduit().getClient();
                Long timeout = (Long) this.requestContext.get(RECEIVE_TIMEOUT); 
                if (timeout != null) {
                    clientPolicy.setReceiveTimeout(timeout);
                }
                timeout = (Long) this.requestContext.get(CONNECTION_TIMEOUT);
                if (timeout != null) {
                    clientPolicy.setConnectionTimeout(timeout);
                }
                
                javax.ws.rs.core.Response response = this.client.invoke(httpMethod, payload);
                this.responseContext.put(WSConnection.STATUS_CODE, response.getStatus());
                this.responseContext.putAll(response.getMetadata());

                ArrayList contentTypes = (ArrayList)this.responseContext.get("content-type"); //$NON-NLS-1$
                String contentType = contentTypes != null ? (String)contentTypes.get(0):"application/octet-stream"; //$NON-NLS-1$
                return new HttpDataSource(url, (InputStream)response.getEntity(), contentType);
            } catch (IOException e) {
                throw new WebServiceException(e);
            }
        }

        @Override
        public Map<String, Object> getRequestContext() {
            return this.requestContext;
        }

        @Override
        public Map<String, Object> getResponseContext() {
            return this.responseContext;
        }

        @Override
        public Binding getBinding() {
            throw new UnsupportedOperationException();
        }

        @Override
        public EndpointReference getEndpointReference() {
            throw new UnsupportedOperationException();
        }

        @Override
        public <T extends EndpointReference> T getEndpointReference(Class<T> clazz) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Response<DataSource> invokeAsync(DataSource msg) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Future<?> invokeAsync(DataSource msg,AsyncHandler<DataSource> handler) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void invokeOneWay(DataSource msg) {
            throw new UnsupportedOperationException();
        }
    }
    
    private static final class HttpDataSource implements DataSource {
        
        private final URL url;
        private InputStream content;
        private String contentType;

        private HttpDataSource(URL url, InputStream entity, String contentType) {
            this.url = url;
            this.content = entity;
            this.contentType = contentType;
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getName() {
            return this.url.getPath();
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return this.content;
        }

        @Override
        public String getContentType() {
            return this.contentType;
        }
    }

}
