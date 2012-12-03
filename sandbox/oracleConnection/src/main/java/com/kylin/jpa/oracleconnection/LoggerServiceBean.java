package com.kylin.jpa.oracleconnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;

@Stateless  
@Remote(LoggerService.class)  
@Local(LoggerServiceLocal.class)
public class LoggerServiceBean implements LoggerServiceLocal {

	@PersistenceContext
	protected EntityManager em;
	
	public void log() {
		
		Session session = (Session)em.getDelegate();
		Connection conn = session.connection();
		
		Query query = em.createQuery("select u from User as u");  
		System.out.println("USER table record count: " + query.getResultList().size());
		
		try {
			print(conn);
		} catch (Exception e) {
		} 
	}
	
	private void print(Connection conn) throws SQLException {
		
		StringBuffer sb = new StringBuffer();

		Class[] cls = conn.getClass().getInterfaces();

		sb.append("Connection Implmented Interface: \n");
		for(Class c : cls){
			sb.append("  " + c + "\n");
		}
		
		sb.append("Connection Class Hierarchy: \n");
		sb.append("  " + conn.getClass() + "\n");
		sb.append("  " + conn.getClass().getSuperclass() + "\n");
		sb.append("  " + conn.getClass().getSuperclass().getSuperclass() + "\n");
		
		System.out.println(sb.toString());
		
		conn.close();
	
	}

}
