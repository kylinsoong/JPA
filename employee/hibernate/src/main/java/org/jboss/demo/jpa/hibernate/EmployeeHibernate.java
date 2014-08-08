package org.jboss.demo.jpa.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jboss.demo.jpa.model.util.SamplePopulation;

public class EmployeeHibernate {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("org.jboss.demo.jpa.model");
		
		try {
			createNewEmployees(factory);
			executeNamedQuery(factory);
		} finally {
			factory.close();
		}
	}

	private static void executeNamedQuery(EntityManagerFactory factory) {
		
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

	private static void prompt(String string, List resultList) {
		System.out.println();
		System.out.println("JPA NamedQuery " + string);
		for(Object obj : resultList){
			System.out.println(obj);
		}
	}

	private static void createNewEmployees(EntityManagerFactory factory) {
		
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
	}

}
