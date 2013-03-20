package com.kylin.man.service.stateful;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ejb.Remote;
import javax.ejb.Stateful;
//import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.Clustered;

@Clustered
@Stateful
@Remote(StatefulService.class)
public class StatefulSession implements StatefulService {
	
	private static final Logger log = Logger.getLogger(StatefulSession.class);
	
	AtomicLong value = new AtomicLong();
	
	List<Object> values = new ArrayList<Object>();

	public void ping() {
		
		values.add(value.getAndIncrement());
		
		log.info("Current Values: " + values);
	}

}
