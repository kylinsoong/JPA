package com.kylin.man.ui.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.kylin.man.po.Man;
import com.kylin.man.service.FacadeService;

@SessionScoped
public class ManController implements Serializable {

	private static final long serialVersionUID = 8553376450841183828L;

	List<Man> mans = null;

	@EJB
	private FacadeService service;

	@Produces
	@Named
	public List<Man> getMans() {

		return mans;
	}

	@PostConstruct
	public void initMans() {
		System.out.println(service);

		mans = service.getMans();
	}

}
