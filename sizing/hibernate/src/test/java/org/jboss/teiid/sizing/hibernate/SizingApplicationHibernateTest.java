package org.jboss.teiid.sizing.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

@Ignore
public class SizingApplicationHibernateTest {
	
	static EntityManagerFactory factory;

	@BeforeClass 
	public static void init() {
		factory = Persistence.createEntityManagerFactory("org.jboss.demo.jpa.model");
	}

	@AfterClass 
	public static void destory() {
		factory.close();
	}
}
