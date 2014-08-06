package org.jboss.demo.jpa.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jboss.demo.jpa.model.util.SamplePopulation;

public class EmployeeHibernate {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("org.jboss.demo.jpa.model");
		
		EntityManager em = factory.createEntityManager(); 
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		SamplePopulation.getInstance().createNewAddress(em, 5);
		
		tx.commit();
		
		em.close();

		factory.close();
	}

}
