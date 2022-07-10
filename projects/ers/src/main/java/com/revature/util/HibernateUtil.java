package com.revature.util;

import com.revature.models.ERSReimbStatus;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    private static SessionFactory sf;

    public static SessionFactory getSessionFactory() {

        if (sf == null || sf.isClosed()) {
            //https://docs.jboss.org/hibernate/orm/5.6/userguide/html_single/Hibernate_User_Guide.html#bootstrap-native
            ServiceRegistry standardRegistry =
                    new StandardServiceRegistryBuilder().configure().build();

            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(ERSReimbStatus.class)
                    .buildMetadata();

            sf = metadata.getSessionFactoryBuilder().build();
        }
        return sf;
    }
}