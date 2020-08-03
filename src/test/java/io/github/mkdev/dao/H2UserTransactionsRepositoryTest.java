package io.github.mkdev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mkdev.database.H2ConnectionManager;
import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import io.github.mkdev.model.UserTransactions;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class H2UserTransactionsRepositoryTest {

  public H2UserTransactionsRepository init() throws SQLException {
    H2ConnectionManager connectionManager = H2ConnectionManager.create();
    Connection connection = connectionManager.getConnection();
    return new H2UserTransactionsRepository(connection);
  }

  @Test
  public void whenSaveIsWork() {
    try {
      H2UserTransactionsRepository h2UserTransactionsRepository = init();
      UUID uuid = UUID.randomUUID();
      UUID uuid2 = UUID.randomUUID();
      BigDecimal bigDecimal = new BigDecimal("599");
      BigDecimal price = new BigDecimal("700");
      Optional<UserTransactions>
          user = h2UserTransactionsRepository
          .save(new UserTransactions(new User(uuid, "User", new Role("User 22")),
          new Item(uuid2, "phone", "mobile phone", new Market("shop"), bigDecimal), 1, price));
      assertTrue(user.isPresent());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenUpdateUserTransactionIsWork() {
    try {
      H2UserTransactionsRepository h2UserRepository = init();
      UUID uuid = UUID.randomUUID();
      UUID uuid2 = UUID.randomUUID();
      BigDecimal bigDecimal = new BigDecimal("699");
      BigDecimal price = new BigDecimal("700");
      UserTransactions userTransactions =
          new UserTransactions(new User(uuid, "User24", new Role("User 35")),
          new Item(uuid2, "phone", "smartphone", new Market("svyznoy"), bigDecimal), 5, price);
      Optional<UserTransactions> optional = h2UserRepository.save(userTransactions);
      String id = h2UserRepository.selectID("user_transaction", "count", "5");
      h2UserRepository.updateUserTransaction("10", id, "count");
      UserTransactions ut = optional.get();
      String oldCount = ut.getCount().toString();
      String exp = "10";
      assertNotEquals(exp, oldCount);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenDeleteUserIsWork() {
    try {
      H2UserTransactionsRepository h2UserRepository = init();
      UUID uuid = UUID.randomUUID();
      UUID uuid2 = UUID.randomUUID();
      BigDecimal bigDecimal = new BigDecimal("300");
      BigDecimal price = new BigDecimal("500");
      UserTransactions user = new UserTransactions(new User(uuid, "Andryi", new Role("Admin7")),
          new Item(uuid2, "mouse", "mouse for PC", new Market("DNS"), bigDecimal), 1, price);
      h2UserRepository.save(user);
      h2UserRepository.deleteUserTransaction(user);
      String id2 = h2UserRepository.selectID("user_transaction", "price", "500");
      assertNull(id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenSeclectIsWork() {
    try {
      H2UserTransactionsRepository h2UserRepository = init();
      UUID uuid = UUID.randomUUID();
      UUID uuid2 = UUID.randomUUID();
      BigDecimal bigDecimal = new BigDecimal("250");
      BigDecimal price = new BigDecimal("660");
      UserTransactions user = new UserTransactions(new User(uuid, "Siry", new Role("NewUser")),
          new Item(uuid2, "printer", "printer for PC", new Market("Ali"), bigDecimal), 1, price);
      h2UserRepository.save(user);
      Map<String, String> map = h2UserRepository.selectUserTransaction();
      String s = map.get("660");
      String exp = "660";
      assertEquals(exp, s);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}





