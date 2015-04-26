package org.jboss.jca.samples.helloworld;

public class HelloWorldConnectionImpl implements HelloWorldConnection {
	
	private HelloWorldManagedConnection mc;
	
	private HelloWorldManagedConnectionFactory mcf;

	public HelloWorldConnectionImpl(HelloWorldManagedConnection mc, HelloWorldManagedConnectionFactory mcf) {
		this.mc = mc;
		this.mcf = mcf;
	}

	public String helloWorld() {
		return helloWorld(((HelloWorldResourceAdapter)mcf.getResourceAdapter()).getName());
	}

	public String helloWorld(String name) {
		return mc.helloWorld(name);
	}

	public void close() {
		mc.closeHandle(this);
	}

}
