package org.jboss.jca.samples.helloworld;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ConfigProperty;
import javax.resource.spi.Connector;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.TransactionSupport;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

/**
 * 
 * 
 * @author kylin
 *
 */
@Connector(
		displayName = {"helloworld"},
		description = {"HelloWorld ResourceAdapter"},
		reauthenticationSupport = false,
		transactionSupport = TransactionSupport.TransactionSupportLevel.NoTransaction,
		version = "1.0")
public class HelloWorldResourceAdapter implements ResourceAdapter {
	
	@ConfigProperty(defaultValue = "WF 8", supportsDynamicUpdates = true)
	private String name;
	
	public HelloWorldResourceAdapter(){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public void start(BootstrapContext ctx) throws ResourceAdapterInternalException {

	}

	public void stop() {

	}

	public void endpointActivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) throws ResourceException {

	}

	public void endpointDeactivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) {

	}

	public XAResource[] getXAResources(ActivationSpec[] specs) throws ResourceException {
		return null;
	}

	@Override
	public int hashCode() {
		int result = 17;
		
		if (name != null){
			result += 31 * result + 7 * name.hashCode();
		} else {
			result += 31 * result + 7;
		}
		return result;
	}

	@Override
	public boolean equals(Object other) {
		
		if (other == null) {
			return false;
		}
		
		if(other == this) {
			return true;
		}
		
		if (!(other instanceof HelloWorldResourceAdapter)) {
			return false;
		}
		
		HelloWorldResourceAdapter obj = (HelloWorldResourceAdapter)other;
		
		boolean result = true;
		if (result) {
			if (name == null) {
				result = obj.getName() == null;
			} else {
				result = name.equals(obj.getName());
			}
		}
		return result;
	}

}
