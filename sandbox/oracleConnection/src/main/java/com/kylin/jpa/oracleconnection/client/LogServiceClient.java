package com.kylin.jpa.oracleconnection.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.kylin.jpa.oracleconnection.LoggerService;

public class LogServiceClient {

	public static void main(String[] args) throws NamingException {

		Context ctx = new InitialContext();
		LoggerService client = (LoggerService) ctx.lookup("LoggerServiceBean/remote-com.kylin.jpa.oracleconnection.LoggerService");
		client.log();
	}

}
