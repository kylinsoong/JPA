package org.jboss.jca.samples.helloworld;

import java.io.Serializable;

import javax.naming.Referenceable;
import javax.resource.ResourceException;

public interface HelloWorldConnectionFactory extends Serializable, Referenceable {

	public HelloWorldConnection getConnection() throws ResourceException;
	
}
