package org.jboss.demo.jpa.eclipselink;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jboss.demo.jpa.model.util.SamplePopulation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeEclipseLinkTest {

	static EntityManagerFactory factory;

	@BeforeClass 
	public static void init() {
		factory = Persistence.createEntityManagerFactory("org.jboss.demo.jpa.model");
	}
	
	@Test
	public void createNewEmployees() {
		
		System.out.println("create 3 Employees");
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = factory.createEntityManager(); 
			tx = em.getTransaction();
			tx.begin();
			SamplePopulation.getInstance().createNewEmployees(em, 3);
			tx.commit();
		} finally {
			em.close();
		}
		
		executeNamedQuery();
	}
	
	private void executeNamedQuery() {
		
		EntityManager em = factory.createEntityManager();
		
		try {
			prompt("Employee.findAll", em.createNamedQuery("Employee.findAll").getResultList());
			prompt("Employee.findByName", em.createNamedQuery("Employee.findByName").getResultList());
			prompt("Employee.count", em.createNamedQuery("Employee.count").getResultList());
			prompt("Employee.address", em.createNamedQuery("Employee.address").getResultList());
		} finally {
			em.close();
		}
		
	}

	private void prompt(String string, List resultList) {
		System.out.println();
		System.out.println("JPA NamedQuery " + string);
		for(Object obj : resultList){
			System.out.println(obj);
		}
	}

	@AfterClass 
	public static void destory() {
		factory.close();
	}
}
