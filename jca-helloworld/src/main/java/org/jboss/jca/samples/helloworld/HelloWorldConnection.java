package org.jboss.jca.samples.helloworld;

public interface HelloWorldConnection {

	public String helloWorld();
	
	public String helloWorld(String name);
	
	public void close();
}
