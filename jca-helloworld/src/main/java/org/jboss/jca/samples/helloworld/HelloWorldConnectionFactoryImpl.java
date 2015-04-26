package org.jboss.jca.samples.helloworld;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;

public class HelloWorldConnectionFactoryImpl implements HelloWorldConnectionFactory {

	private static final long serialVersionUID = 1923361419597202546L;
	
	private Reference reference;
	
	private HelloWorldManagedConnectionFactory mcf;
	private ConnectionManager connectionManager;

	public HelloWorldConnectionFactoryImpl( HelloWorldManagedConnectionFactory mcf, ConnectionManager cxManager) {
		this.mcf = mcf;
		this.connectionManager = cxManager;
	}

	public Reference getReference() throws NamingException {
		return reference;
	}
	
	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public HelloWorldConnection getConnection() throws ResourceException {
		return (HelloWorldConnection)connectionManager.allocateConnection(mcf, null);
	}

}
