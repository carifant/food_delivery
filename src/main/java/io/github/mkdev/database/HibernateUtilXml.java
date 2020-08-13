package io.github.mkdev.database;

import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import io.github.mkdev.model.UserTransactions;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtilXml {


  private static SessionFactory sessionFactory;

  static {
    try {
      StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
          .configure("hibernate.cfg.xml").build();
      Metadata metadata = new MetadataSources(standardRegistry)
          .getMetadataBuilder().build();
      sessionFactory = metadata.getSessionFactoryBuilder().build();
    } catch (HibernateException exception) {
      System.out.println("problem creating session factory!");
    }
  }


  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}



