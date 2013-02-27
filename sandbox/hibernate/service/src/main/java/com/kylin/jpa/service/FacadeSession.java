package com.kylin.jpa.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.kylin.jpa.po.User;

@Stateless
@Remote(FacadeService.class)
public class FacadeSession implements FacadeService{

	@Inject
	private EntityManager em;
	
	@Inject
	private Logger log;

	public void addUser(User user) {
		
		log.debug("add User " + user);

		em.persist(user);
	}
	
	public void addUsers(List<User> users) {
		
		log.debug("add User List, List Size: " + users.size());

		for(User u : users) {
			em.persist(u);
		}
	}

	public List<User> getUsers() {
		
		log.debug("query all exist users");
		
		return em.createQuery("select u from User as u").getResultList();
	}

	public User getUser(long id) {
		
		log.debug("get User by id: " + id);
		
		return em.find(User.class, id);
	}

	public void removeUser(long id) {
		
		log.debug("remove User by id: " + id);
		
		Object obj = em.find(User.class, id);
		em.remove(obj);
	}

	public void updateUser(User user) {
		
		log.debug("update User " + user);
		
		em.merge(user);
	}

	public String ping() {
		return "ping success from " + FacadeService.class.getSimpleName();
	}

	

}
