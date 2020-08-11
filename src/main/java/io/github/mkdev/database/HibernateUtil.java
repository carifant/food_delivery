package io.github.mkdev.database;

import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import io.github.mkdev.model.UserTransactions;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {
  private static StandardServiceRegistry registry;
  private static SessionFactory sessionFactory;

  /**
   * Summary text.
   * method for creation session factory
   *
   * @return session factory
   */
  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.postgresql.Driver");
        settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/delivery_food");
        settings.put(Environment.USER, "postgres");
        settings.put(Environment.PASS, "password");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        // Maximum waiting time for a connection from the pool
        settings.put("hibernate.hikari.connectionTimeout", "20000");
        // Minimum number of ideal connections in the pool
        settings.put("hibernate.hikari.minimumIdle", "10");
        // Maximum number of actual connection in the pool
        settings.put("hibernate.hikari.maximumPoolSize", "20");
        // Maximum time that a connection is allowed to sit ideal in the pool
        settings.put("hibernate.hikari.idleTimeout", "300000");
        Configuration configuration = new Configuration();
        configuration.setProperties(settings);

        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Market.class);
        configuration.addAnnotatedClass(Item.class);
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
   * Use for realise singleton.
   */
  public static void shutdown() {
    if (registry != null) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }
}

