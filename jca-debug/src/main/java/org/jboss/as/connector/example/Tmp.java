package org.jboss.as.connector.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.resource.spi.ResourceAdapter;

import org.jboss.jca.common.api.metadata.common.CommonAdminObject;
import org.jboss.jca.common.api.metadata.common.CommonPool;
import org.jboss.jca.common.api.metadata.common.CommonSecurity;
import org.jboss.jca.common.api.metadata.common.CommonTimeOut;
import org.jboss.jca.common.api.metadata.common.CommonValidation;
import org.jboss.jca.common.api.metadata.common.FlushStrategy;
import org.jboss.jca.common.api.metadata.common.TransactionSupportEnum;
import org.jboss.jca.common.api.metadata.common.v10.CommonConnDef;
import org.jboss.jca.common.api.metadata.ra.Connector;
import org.jboss.jca.common.api.metadata.ra.ResourceAdapter1516;
import org.jboss.jca.common.api.validator.ValidateException;
import org.jboss.jca.common.metadata.common.CommonPoolImpl;
import org.jboss.jca.common.metadata.common.CommonSecurityImpl;
import org.jboss.jca.common.metadata.common.CommonTimeOutImpl;
import org.jboss.jca.common.metadata.common.CommonValidationImpl;
import org.jboss.jca.common.metadata.common.v10.CommonConnDefImpl;
import org.jboss.jca.common.metadata.ra.RaParser;
import org.jboss.jca.common.metadata.resourceadapter.v10.ResourceAdapterImpl;
import org.jboss.jca.deployers.common.CommonDeployment;


public class Tmp {

    public static void main(String[] args) throws Throwable {
        
        String path = "ironjacamar.xml";
        
        File file = new File(path);
        URL url = file.toURI().toURL();

        InputStream xmlStream = new FileInputStream(file);
        RaParser raParser = new RaParser();
        raParser.setSystemPropertiesResolved(true);
        Connector connector = raParser.parse(xmlStream);
        
//        String archive = null ; 
//        TransactionSupportEnum transactionSupport = null ;
//        List<CommonConnDef> connectionDefinitions = null ;
//        List<CommonAdminObject> adminObjects = null ;
//        Map<String, String> configProperties = null ; 
//        List<String> beanValidationGroups = null ; 
//        String bootstrapContext = null ;
//        org.jboss.jca.common.api.metadata.resourceadapter.ResourceAdapter ra = new ResourceAdapterImpl(archive, transactionSupport, connectionDefinitions, adminObjects, configProperties, beanValidationGroups, bootstrapContext);
        
        TeiidResourceAdapter ra = TeiidResourceAdapter.create();
        
        Map<String, String> configProperties = new HashMap<String, String>();
        String className = "org.teiid.resource.adapter.ws.WSManagedConnectionFactory";
        String securityDomain = null;
        ra.addConnectionDefinition(createConnDef(configProperties, className, securityDomain));
        TeiidRaDeployer deployer = new TeiidRaDeployer(ra);
        
        
        CommonDeployment dep = deployer.doDeploy(url, "webservice", file.getParentFile(), Tmp.class.getClassLoader(), connector);
        
        
        System.out.println(dep.getCfs());
    }
    
    private static CommonConnDef createConnDef(Map<String, String> configProperties, String className, String securityDomain) throws ValidateException {
        
        String jndiName = "teiid-embedded";
        String poolName = "teiid-embedded";
        Boolean enabled = true;
        Boolean useJavaContext = true;
        Boolean useCcm = true;
                
        Integer allocationRetry = null;
        Long allocationRetryWaitMillis = null;
        Long blockingTimeoutMillis = null;
        Long idleTimeoutMinutes = null;
        Integer xaResourceTimeout = null;
        CommonTimeOut timeOut = new CommonTimeOutImpl(blockingTimeoutMillis, idleTimeoutMinutes, allocationRetry, allocationRetryWaitMillis, xaResourceTimeout);
        
        boolean backgroundValidation = false;
        Long backgroundValidationMillis = null;
        boolean useFastFail = false;
        CommonValidation validation = new CommonValidationImpl(backgroundValidation, backgroundValidationMillis, useFastFail);
        
        int maxPoolSize = 20;
        int minPoolSize = 0;
        boolean prefill = false;
        boolean useStrictMin = false;
        final FlushStrategy flushStrategy = FlushStrategy.FAILING_CONNECTION_ONLY;
        CommonPool pool = new CommonPoolImpl(minPoolSize, maxPoolSize, prefill, useStrictMin, flushStrategy);
        
        CommonSecurity security = null;
        if(securityDomain != null) {
            security = new CommonSecurityImpl(securityDomain, null, false);
        }
        
        return new CommonConnDefImpl(configProperties, className, jndiName, poolName, enabled, useJavaContext, useCcm, pool, timeOut, validation, security, null, false);
    }

    public static class TeiidResourceAdapter extends ResourceAdapterImpl {

        private static final long serialVersionUID = 2993792168984047340L;

        private TeiidResourceAdapter(String archive,
                TransactionSupportEnum transactionSupport,
                List<CommonConnDef> connectionDefinitions,
                List<CommonAdminObject> adminObjects,
                Map<String, String> configProperties,
                List<String> beanValidationGroups, String bootstrapContext) {
            super(archive, transactionSupport, connectionDefinitions, adminObjects, configProperties, beanValidationGroups, bootstrapContext);
        }
        
        public static TeiidResourceAdapter create(){
            String archive = "teiid-embedded";
            Map<String, String> configProperties = new HashMap<String, String>(0);
            List<CommonConnDef> connectionDefinitions = new ArrayList<CommonConnDef>(0);
            List<CommonAdminObject> adminObjects = new ArrayList<CommonAdminObject>(0);
            TransactionSupportEnum transactionSupport = TransactionSupportEnum.NoTransaction;
            List<String> beanValidationGroups = null;
            String bootstrapContext = null;
            return new TeiidResourceAdapter(archive, transactionSupport, connectionDefinitions, adminObjects, configProperties, beanValidationGroups, bootstrapContext);
        }
        
        public void addConfigProperty(String name, String value) {
            configProperties.put(name, value);
        }

        public void addConnectionDefinition(CommonConnDef value) {
            connectionDefinitions.add(value);
        }

        public int connectionDefinitionSize() {
                return connectionDefinitions.size();
        }

        public void addAdminObject(CommonAdminObject value) {
            adminObjects.add(value);
        }

        public int adminObjectSize() {
            return adminObjects.size();
        }
        
    }

}
