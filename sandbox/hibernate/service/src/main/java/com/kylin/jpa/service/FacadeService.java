package com.kylin.jpa.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class FacadeService {

	@Inject
	private EntityManager em;

}
