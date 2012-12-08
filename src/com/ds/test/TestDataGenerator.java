package com.ds.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @author adlakha.vaibhav
 */
public class TestDataGenerator {

static SessionFactory sessionFactory = null;

  public static Session getHibernateSession() {
    if (sessionFactory == null) {
      sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }
    return sessionFactory.openSession();
  }

  public static void main(String[] args) {
    Session session = getHibernateSession();

    try {


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  
}
