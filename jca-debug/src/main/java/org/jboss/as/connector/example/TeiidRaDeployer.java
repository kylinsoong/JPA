package org.jboss.as.connector.example;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

import javax.resource.ResourceException;
import javax.resource.spi.ResourceAdapter;
import javax.transaction.TransactionManager;

import org.jboss.jca.common.api.metadata.ironjacamar.IronJacamar;
import org.jboss.jca.common.api.metadata.ra.ConfigProperty;
import org.jboss.jca.common.api.metadata.ra.Connector;
import org.jboss.jca.core.api.connectionmanager.ccm.CachedConnectionManager;
import org.jboss.jca.core.spi.mdr.AlreadyExistsException;
import org.jboss.jca.core.spi.transaction.TransactionIntegration;
import org.jboss.jca.deployers.DeployersLogger;
import org.jboss.jca.deployers.common.AbstractResourceAdapterDeployer;
import org.jboss.jca.deployers.common.CommonDeployment;
import org.jboss.jca.deployers.common.DeployException;
import org.jboss.jca.validator.ValidatorException;
import org.jboss.logging.Logger;
import org.jboss.security.SubjectFactory;

public class TeiidRaDeployer extends AbstractResourceAdapterDeployer {
    
    private static final DeployersLogger DEPLOYERS_LOGGER = Logger.getMessageLogger(DeployersLogger.class, TeiidRaDeployer.class.getName());
    
    private org.jboss.jca.common.api.metadata.resourceadapter.ResourceAdapter ra;

    public TeiidRaDeployer(org.jboss.jca.common.api.metadata.resourceadapter.ResourceAdapter ra) {
        super(true);
        this.ra = ra ;
        this.setConfiguration(new JcaTeiidConfiguration());
    }
    
    public CommonDeployment doDeploy(URL url, String deploymentName, File root, ClassLoader cl, Connector cmd) throws AlreadyExistsException, ClassNotFoundException, DeployException, ResourceException, ValidatorException, Throwable {
        CommonDeployment dep = this.createObjectsAndInjectValue(url, deploymentName, root, cl, cmd, null, ra);
        return dep;
    }

    @Override
    protected SubjectFactory getSubjectFactory(String securityDomain)throws DeployException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected CachedConnectionManager getCachedConnectionManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected File getReportDirectory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void registerResourceAdapterToMDR(URL url, File root,
            Connector cmd, IronJacamar ijmd) throws AlreadyExistsException {
        // TODO Auto-generated method stub

    }

    @Override
    protected String registerResourceAdapterToResourceAdapterRepository(
            ResourceAdapter instance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected TransactionManager getTransactionManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected TransactionIntegration getTransactionIntegration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected PrintWriter getLogPrintWriter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String[] bindConnectionFactory(URL url, String deploymentName,
            Object cf) throws Throwable {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String[] bindConnectionFactory(URL url, String deploymentName,
            Object cf, String jndiName) throws Throwable {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String[] bindAdminObject(URL url, String deploymentName, Object ao)
            throws Throwable {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String[] bindAdminObject(URL url, String deploymentName,
            Object ao, String jndiName) throws Throwable {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected boolean checkConfigurationIsValid() {
        return false;
    }

    @Override
    protected boolean checkActivation(Connector cmd, IronJacamar ijmd) {
        return true;
    }

    @Override
    protected Object initAndInject(String value, List<? extends ConfigProperty> cpm, ClassLoader cl)throws DeployException {
        try {
            return cl.loadClass(value).newInstance();
        } catch (Exception e) {
            throw new DeployException(e.getMessage());
        } 
    }

    @Override
    protected DeployersLogger getLogger() {
        return DEPLOYERS_LOGGER;
    }

}
