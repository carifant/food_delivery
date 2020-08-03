package io.github.mkdev;

import io.github.mkdev.dao.H2BaseRepository;
import io.github.mkdev.dao.H2ItemRepository;
import io.github.mkdev.dao.H2MarketRepository;
import io.github.mkdev.dao.H2RoleRepository;
import io.github.mkdev.dao.H2UserRepository;
import io.github.mkdev.dao.H2UserTransactionsRepository;
import io.github.mkdev.dao.Repository;
import io.github.mkdev.database.H2ConnectionManager;
import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import io.github.mkdev.model.UserTransactions;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;


public class App {

  /**
   * Input program method. Allow start project code.
   */

  public static void main(String[] args) throws SQLException {
    H2ConnectionManager connectionManager = H2ConnectionManager.create();
    Connection connection = connectionManager.getConnection();
    Repository<Role> h2RoleRepository = new H2RoleRepository(connection);
    Optional<Role> role = h2RoleRepository.save(new Role("ADMIN"));



    new H2ItemRepository(connection).save(new Item("car", "audi",
        new Market(UUID.randomUUID(), "shopCar", null), new BigDecimal("76600.55")));

    new H2MarketRepository(connection).save(new Market("SHOP", new User(UUID.randomUUID(),
        "User2", null)));

    new H2UserRepository(connection).save(new User("Alisa", new Role(UUID.randomUUID(),
        "User")));
    new H2UserTransactionsRepository(connection)
         .save(new UserTransactions(new User(UUID.randomUUID(), "New_User", new Role("User 5")),
           new Item(UUID.randomUUID(), "phone", "mobile phone", new Market("shop"),
           new BigDecimal(3500)), 1, new BigDecimal(1000)));
  }
}
