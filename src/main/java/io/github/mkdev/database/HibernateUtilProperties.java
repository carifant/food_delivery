package io.github.mkdev.database;

import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import io.github.mkdev.model.UserTransactions;
import io.github.mkdev.other.LoadProperties;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtilProperties {

  private static StandardServiceRegistry registry;
  private static SessionFactory sessionFactory;

  /**
   * Method create new SessionFactory.
   *
   */
  public static SessionFactory getSessionFactory() {

    if (sessionFactory == null) {
      try {

        Configuration configuration = new Configuration();
        Properties settings = new LoadProperties().getProp();
        configuration.setProperties(settings).configure();

        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Market.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(UserTransactions.class);

        registry = new StandardServiceRegistryBuilder()
          .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(registry);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return sessionFactory;
  }

  /**
   * method is closing registry.
   */
  public static void shutdown() {
    if (registry != null) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }
}
