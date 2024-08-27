package com.cpi.is.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HBUtil {

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				registry = new StandardServiceRegistryBuilder().configure().build();
				sessionFactory = new MetadataSources(registry)
						.getMetadataBuilder().build().getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				destroyRegistry();
			}
		}
		return sessionFactory;
	}
	
	public static void destroyRegistry() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
}