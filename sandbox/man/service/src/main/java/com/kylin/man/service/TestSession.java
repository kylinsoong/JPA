package com.kylin.man.service;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.kylin.man.po.util.POUtil;

@Stateless
@Remote(TestService.class)
public class TestSession implements TestService {
	
	@Inject
	private Logger log;

	public void jaxbTest() {
		log.info(POUtil.convertToString(POUtil.newMan()));
	}

}
