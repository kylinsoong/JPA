package org.jboss.jca.samples.helloworld;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

public class HelloWorldManagedConnection implements ManagedConnection {
	
	private HelloWorldManagedConnectionFactory mcf;
	
	private PrintWriter logWriter;
	
	private List<ConnectionEventListener> listeners;
	
	private Object connection;

	public HelloWorldManagedConnection( HelloWorldManagedConnectionFactory mcf) {
		this.mcf = mcf;
		this.logWriter = null;
		this.listeners = new ArrayList<ConnectionEventListener>(1);
		this.connection = null;
	}

	public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
		connection = new HelloWorldConnectionImpl(this, mcf);
		return connection;
	}

	public void destroy() throws ResourceException {
		this.connection = null;
	}

	public void cleanup() throws ResourceException {

	}

	public void associateConnection(Object connection) throws ResourceException {
		this.connection = connection;
	}

	public void addConnectionEventListener(ConnectionEventListener listener) {
		
		if (listener == null) {
			throw new IllegalArgumentException("Listener is null");
		}
		
		listeners.add(listener);
	}

	public void removeConnectionEventListener(ConnectionEventListener listener) {
		
		if (listener == null) {
			throw new IllegalArgumentException("Listener is null");
		}
		
		listeners.remove(listener);
	}

	public XAResource getXAResource() throws ResourceException {
		throw new NotSupportedException("GetXAResource not supported");
	}

	public LocalTransaction getLocalTransaction() throws ResourceException {
		throw new NotSupportedException("LocalTransaction not supported");
	}

	public ManagedConnectionMetaData getMetaData() throws ResourceException {
		return new HelloWorldManagedConnectionMetaData();
	}

	public void setLogWriter(PrintWriter out) throws ResourceException {
		this.logWriter = out;
	}

	public PrintWriter getLogWriter() throws ResourceException {
		return logWriter;
	}
	
	String helloWorld(String name) {
		return "Hello World, " + name + " !";
	}
	
	void closeHandle(HelloWorldConnection handle) {
		ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
		event.setConnectionHandle(handle);
		
		for (ConnectionEventListener cel : listeners) {
			cel.connectionClosed(event);
		}
	}

}
