package com.example.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
    public static SessionFactory sessionFactory;

    private HibernateUtility() {
    }

    public static synchronized SessionFactory getSessionFactory()
    {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}