package io.github.mkdev;

import io.github.mkdev.database.HibernateUtilXml;
import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import io.github.mkdev.model.UserTransactions;
import java.math.BigDecimal;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

  /**
   * Input program method. Allow start project code.
   */
  public static void main(String[] args) {

    Transaction transaction;
    try (Session session = HibernateUtilXml.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Role role = new Role("ADMIN");
      User user = new User("vasilisa", role);
      Market market = new Market("DNS", user);
      Item item = new Item("phone", "mobile Phone", market, new BigDecimal(1000));
      UserTransactions ut = new UserTransactions(user, item, 10, new BigDecimal(50));
      session.save(ut);
      transaction.commit();
      Query selectQuery = session
          .createSQLQuery(
          "SELECT id, user_id, item_id, count, price,"
            + " total FROM user_transaction WHERE count = 10 ")
          .addEntity(UserTransactions.class);
      UserTransactions fromDB = (UserTransactions) selectQuery.getResultList().get(0);

      System.out.println(fromDB);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

