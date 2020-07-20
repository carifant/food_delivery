package io.github.mkdev.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import io.github.mkdev.model.UserTransactions;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTransactionServiceTest {

  @Test
  public void whenUserTransactionWasCreated() {
    UserTransactionService service = new UserTransactionService();
    Role role = new Role("Admin");
    User user = new User("User", role);
    Market market = new Market("market", user);
    BigDecimal bigD = new BigDecimal(10);
    Item item = new Item("Phone", "Apple Phone", market, bigD);
    UserTransactions userTransactions = service.createUserTransactions(user, item, 15, bigD);
    assertNotNull(userTransactions);
  }

  @Test
  public void whenUserTransactionNotCreated() {
    UserTransactionService service = null;
    Role role = new Role("Admin");
    User user = new User("User", role);
    Market market = new Market("market", user);
    BigDecimal bigD = new BigDecimal(10);
    Item item = new Item("Phone", "Apple Phone", market, bigD);
    Assertions.assertThrows(NullPointerException.class, () -> {
      service.createUserTransactions(user, item, 15, bigD);
    });
  }
}
