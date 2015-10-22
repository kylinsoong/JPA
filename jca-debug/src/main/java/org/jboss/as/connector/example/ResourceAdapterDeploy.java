package org.jboss.as.connector.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.as.connector.subsystems.resourceadapters.ModifiableResourceAdapter;
import org.jboss.jca.common.api.metadata.common.CommonAdminObject;
import org.jboss.jca.common.api.metadata.common.CommonSecurity;
import org.jboss.jca.common.api.metadata.common.TransactionSupportEnum;
import org.jboss.jca.common.api.metadata.common.v10.CommonConnDef;
import org.jboss.jca.common.api.validator.ValidateException;
import org.jboss.jca.common.metadata.common.CommonSecurityImpl;
import org.jboss.jca.common.metadata.common.v10.CommonConnDefImpl;
import org.jboss.jca.common.metadata.resourceadapter.v10.ResourceAdapterImpl;

public class ResourceAdapterDeploy {
    
    // To simulate how resource adapter be deployed in WilldFly
    static void simulation() {
        
        
        String archiveOrModule = "org.jboss.teiid.resource-adapter.webservice:main";
        TransactionSupportEnum transactionSupport = TransactionSupportEnum.NoTransaction;
        List<CommonConnDef> connectionDefinitions = new ArrayList<CommonConnDef>(0);
        List<CommonAdminObject> adminObjects = new ArrayList<CommonAdminObject>(0);
        Map<String, String> configProperties = new HashMap<String, String>();
        List<String> beanValidationGroups = null;
        String bootstrapContext = null;
        
        final ModifiableResourceAdapter ra = new ModifiableResourceAdapter(archiveOrModule, transactionSupport, connectionDefinitions, adminObjects, configProperties, beanValidationGroups, bootstrapContext);
        ra.setId("webservice2");
    }

    public static void main(String[] args) throws ValidateException {
        
        List<CommonConnDef> connectionDefinitions = new ArrayList<CommonConnDef>(0);
        List<CommonAdminObject> adminObjects = new ArrayList<CommonAdminObject>(0);
        

//        ResourceAdapterImpl ra = new ResourceAdapterImpl("test", TransactionSupportEnum.NoTransaction, connectionDefinitions, adminObjects, configProperties, null, null);
        
        Map<String, String> configProperties = new HashMap<String, String>();
        configProperties.put("SecurityType", "OAuth");
        
        String className = "org.teiid.resource.adapter.ws.WSManagedConnectionFactory";
        
        CommonSecurity security = new CommonSecurityImpl("oauth-security", null, false);
        
        
        
//        CommonConnDef connDef = new CommonConnDefImpl(configProperties, );
    }

}
