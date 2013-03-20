package com.kylin.man.sql;

import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class SQLSchemaExport {

	public static void main(String[] args) {
//		execute(args[0], args[1], Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]));
		execute("com.kylin.man.po", "export.sql", true, true);
	}

	private static void execute(String persistenceUnitName, String destination, boolean create, boolean format) {
		
		System.out.println("Starting schema export");
		Ejb3Configuration cfg = new Ejb3Configuration().configure(persistenceUnitName, new Properties());
		Configuration hbmcfg = cfg.getHibernateConfiguration();
		SchemaExport schemaExport = new SchemaExport(hbmcfg);
		schemaExport.setOutputFile(destination);
		schemaExport.setFormat(format);
		schemaExport.execute(true, false, false, create);
		System.out.println("Schema exported to " + destination);
	}

}
