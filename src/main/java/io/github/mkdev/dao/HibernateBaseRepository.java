package io.github.mkdev.dao;

import io.github.mkdev.database.HibernateUtilProperties;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class HibernateBaseRepository {

  /**
   * Method saved object.
   */

  @SuppressWarnings("checkstyle:CommentsIndentation")
  public void create(Object o) {
    try (Session session = HibernateUtilProperties.getSessionFactory().openSession()) {

      session.beginTransaction();

      session.save(o);

      session.getTransaction().commit();
    }
  }

  /**
   * Method read object.
   */

  public Object read(String nameOfClass, String column, UUID uuid) {
    try (Session session = HibernateUtilProperties.getSessionFactory().openSession()) {

      Query query = session.createQuery("FROM " + nameOfClass + " where " + column
          + " =  '" + uuid + "'");

      return query.getSingleResult();
    }
  }

  /**
   * Method updated object with string column.
   */

  public void update(String nameOfClass, String column, String newValue, String oldValue) {
    try (Session session = HibernateUtilProperties.getSessionFactory().openSession()) {
      Transaction tr = session.beginTransaction();

      Query query = session.createQuery("update " + nameOfClass + " set " + column
          + " = '" + newValue + "'" + " where " + column + " = '" + oldValue + "'");
      query.executeUpdate();
      tr.commit();

    }
  }

  /**
   * Method updated object with int column.
   */

  public void update(String nameOfClass, String column, int value, int oldValue) {
    try (Session session = HibernateUtilProperties.getSessionFactory().openSession()) {
      Transaction tr = session.beginTransaction();

      Query query = session.createQuery("update " + nameOfClass + " n set n." + column
          + " = " + value + " where n." + column + " = " + oldValue);


      query.executeUpdate();
      tr.commit();

    }
  }

  /**
   * Method deleted object with help string column.
   */

  public void delete(String nameOfClass, String column, String value) {
    try (Session session = HibernateUtilProperties.getSessionFactory().openSession()) {
      Transaction tr = session.beginTransaction();

      Query query =
          session.createQuery("delete " + nameOfClass + " where " + column + " = :param");
      query.setParameter("param", value);
      query.executeUpdate();
      tr.commit();

    }
  }


  /**
   * Method deleted object with help uuid column.
   */

  public void delete(String nameOfClass, String column, UUID uuid) {
    try (Session session = HibernateUtilProperties.getSessionFactory().openSession()) {
      Transaction tr = session.beginTransaction();

      Query query =
          session.createQuery("delete " + nameOfClass + " where " + column + " = :param");
      query.setParameter("param", uuid);
      query.executeUpdate();
      tr.commit();
    }
  }
}


