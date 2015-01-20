package org.jboss.teiid.sizing.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class SizingApplicationHibernate {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("org.jboss.teiid.sizing");
		
		try {
			executeQuery(factory);
		} finally {
			factory.close();
		}
	}

	static void executeQuery(EntityManagerFactory factory) {

		EntityManager em = factory.createEntityManager();
		String sql = "SELECT q FROM Question AS q";
		Query query = em.createQuery(sql);
		prompt(sql, query.getResultList());
	}

	static void prompt(String string, List resultList) {
		System.out.println();
		System.out.println("JPA Query " + string);
		for(Object obj : resultList){
			System.out.println(obj);
		}
	}


}
