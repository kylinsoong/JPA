package org.jboss.jca.samples.helloworld;

import javax.resource.ResourceException;
import javax.resource.spi.ManagedConnectionMetaData;

public class HelloWorldManagedConnectionMetaData implements ManagedConnectionMetaData {

	public String getEISProductName() throws ResourceException {
		return "HelloWorld Resource Adapter";
	}

	public String getEISProductVersion() throws ResourceException {
		return "1.0";
	}

	public int getMaxConnections() throws ResourceException {
		return 0;
	}

	public String getUserName() throws ResourceException {
		return "ksoong";
	}

}
