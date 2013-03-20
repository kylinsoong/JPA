package com.kylin.man.service;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.kylin.man.po.util.POUtil;

@Stateless
@Remote(TestService.class)
public class TestSession implements TestService {
	
	private static final Logger log = Logger.getLogger(TestSession.class);

	public void jaxbTest() {
		log.info(POUtil.convertToString(POUtil.newMan()));
	}

}
