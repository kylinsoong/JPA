package org.jboss.jca.samples.helloworld;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionDefinition;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterAssociation;
import javax.security.auth.Subject;

@ConnectionDefinition(connectionFactory = HelloWorldConnectionFactory.class,
					  connectionFactoryImpl = HelloWorldConnectionFactoryImpl.class,
					  connection = HelloWorldConnection.class,
					  connectionImpl = HelloWorldConnectionImpl.class)
public class HelloWorldManagedConnectionFactory implements ManagedConnectionFactory, ResourceAdapterAssociation {

	private static final long serialVersionUID = -2407379733093059858L;
	
	private ResourceAdapter ra;
	
	private PrintWriter logwriter;
	
	public HelloWorldManagedConnectionFactory() {
		this.ra = null;
		this.logwriter = null;
	}

	public ResourceAdapter getResourceAdapter() {
		return ra;
	}

	public void setResourceAdapter(ResourceAdapter ra) throws ResourceException {
		this.ra = ra;
	}

	public Object createConnectionFactory(ConnectionManager cxManager) throws ResourceException {
		return new HelloWorldConnectionFactoryImpl(this, cxManager);
	}

	public Object createConnectionFactory() throws ResourceException {
		throw new ResourceException("This resource adapter doesn't support non-managed environments");
	}

	public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
		return new HelloWorldManagedConnection(this);
	}

	public ManagedConnection matchManagedConnections(Set connectionSet, Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
		

		ManagedConnection result = null;
		
		Iterator it = connectionSet.iterator();
		while (result == null && it.hasNext()){
			ManagedConnection mc = (ManagedConnection)it.next();
			if (mc instanceof HelloWorldManagedConnection) {
				HelloWorldManagedConnection hwmc = (HelloWorldManagedConnection)mc;
				result = hwmc;
			}
		}
		return result;
	}

	public void setLogWriter(PrintWriter out) throws ResourceException {
		this.logwriter = out;
	}

	public PrintWriter getLogWriter() throws ResourceException {
		return logwriter;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
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
		
		if (!(other instanceof HelloWorldManagedConnectionFactory)){
			return false;
		}
		
		HelloWorldManagedConnectionFactory obj = (HelloWorldManagedConnectionFactory)other;
		
		boolean result = true;
		
		return result;
	}
	
	

}
