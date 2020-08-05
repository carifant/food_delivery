package io.github.mkdev.dao;

import io.github.mkdev.model.Market;
import io.github.mkdev.model.User;
import io.github.mkdev.model.UserTransactions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class H2UserTransactionsRepository extends H2BaseRepository
    implements Repository<UserTransactions> {

  public H2UserTransactionsRepository(Connection connection) {
    super(connection);
  }

  @Override
  public Optional<UserTransactions> save(UserTransactions userTransactions) {
    Optional<UserTransactions> dbUt = Optional.empty();
    String query =
        "INSERT INTO user_transaction(USER_ID, ITEM_ID , COUNT, PRICE, TOTAL) VALUES ( '"
          + userTransactions.getUser().getId()
          + "', '" + userTransactions.getItem().getId() + "', '" + userTransactions.getCount()
          + "', '"
          + userTransactions.getPrice() + "', '" + userTransactions.getTotal() + "')";
    try {
      String id = this.insert(query);
      dbUt = Optional.of(new UserTransactions(
        UUID.fromString(id), userTransactions.getUser(), userTransactions.getItem(),
        userTransactions.getCount(), userTransactions.getPrice()
      ));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return dbUt;
  }

  /**
   * Update program method. Allow to change the date.
   */

  public void updateUserTransaction(String newName, String id, String nameOfColumn) {
    try {
      this.update("user_transaction", nameOfColumn, newName, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Delete program method. Allow to delete the date.
   */

  public void deleteUserTransaction(UserTransactions userTransactions) {
    try {
      String decimal = userTransactions.getPrice().toString();
      String id = this.selectID("user_transaction", "price", decimal);
      this.delete("user_transaction", "price", id);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (NullPointerException x) {
      x.printStackTrace();
      System.out.println("Данного объекта не существует");
    }
  }

  public Map<String, String> selectUserTransaction()
      throws SQLException {
    return selectAll("user_transaction");
  }
}



