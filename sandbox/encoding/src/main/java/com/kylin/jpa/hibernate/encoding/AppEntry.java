package com.kylin.jpa.hibernate.encoding;

import javax.persistence.Persistence;

public class AppEntry {

	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("com.kylin.jpa.hibernate.encoding");

		System.out.println("DONE");
	}
}
