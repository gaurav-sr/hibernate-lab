package com.codeinstance.hibernatelab;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class CommonFactory {

	private static SessionFactory factory = null;
	
	public static SessionFactory getSessionFactory() {
            buildSessionFactory();
            return factory;
	}
	
	private static void buildSessionFactory() {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
            standardServiceRegistryBuilder = standardServiceRegistryBuilder.configure();
            StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder.build();
            factory = configuration.buildSessionFactory(standardServiceRegistry);
        }
}
