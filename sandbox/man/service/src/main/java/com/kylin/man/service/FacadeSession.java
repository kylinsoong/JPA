package com.kylin.man.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.kylin.man.po.Man;

@Stateless
@Remote(FacadeService.class)
public class FacadeSession implements FacadeService{

	@PersistenceContext(name="com.kylin.man.po")
    private EntityManager em;
	
	private static final Logger log = Logger.getLogger(FacadeSession.class);

	public void addMan(Man man) {
		
		log.debug("add Man " + man);

		em.persist(man);
	}
	
	public void addMans(List<Man> mans) {
		
		log.debug("add User List, List Size: " + mans.size());

		for(Man u : mans) {
			em.persist(u);
		}
	}

	public List<Man> getMans() {
		
		log.debug("query all exist mans");
		
		return em.createQuery("select m from Man as m").getResultList();
	}

	public Man getMan(long id) {
		
		log.debug("get Man by id: " + id);
		
		return em.find(Man.class, id);
	}

	public void removeMan(long id) {
		
		log.debug("remove Man by id: " + id);
		
		Object obj = em.find(Man.class, id);
		em.remove(obj);
	}

	public void updateMan(Man man) {
		
		log.debug("update Man " + man);
		
		em.merge(man);
	}

	public String ping() {
		return "Ping success from " + FacadeService.class.getSimpleName();
	}

	

}
