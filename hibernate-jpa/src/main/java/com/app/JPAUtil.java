package com.app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final String persistenceUnitName = "PERSISTENCE";
	private static EntityManagerFactory factory;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if(factory==null) {
			factory=Persistence.createEntityManagerFactory(persistenceUnitName);
		}
		return factory;
	}
	public static void shutdown() {
		if(factory!=null) {
			factory.close();
		}
	}
}
