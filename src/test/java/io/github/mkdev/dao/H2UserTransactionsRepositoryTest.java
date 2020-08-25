package io.github.mkdev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import io.github.mkdev.model.UserTransactions;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class H2UserTransactionsRepositoryTest {

  H2UserTransactionsRepository h2UserTransactionsRepository;

  @BeforeEach
  public void before() {
    h2UserTransactionsRepository = new H2UserTransactionsRepository();
  }

  @Test
  public void whenSaveIsWork() {
    Role role = new Role("user15");
    User user = new User("vilena", role);
    Market market = new Market("dns33", user);
    Item item = new Item("car", "auto", market, new BigDecimal(10000));
    UserTransactions userTransactions = new UserTransactions(user, item, 10, new BigDecimal(500));
    Optional<UserTransactions> save = h2UserTransactionsRepository.save(userTransactions);
    assertTrue(save.isPresent());
    h2UserTransactionsRepository.deleteUserTransaction(userTransactions);
  }


  @Test
  public void whenUpdateUserTransactionIsWork() {
    Role role = new Role("User16");
    User user = new User("Dasha", role);
    Market market = new Market("sitilink", user);
    Item item = new Item("audi", "auto", market, new BigDecimal(50000));
    UserTransactions userTransactions = new UserTransactions(user, item, 1, new BigDecimal(5100));
    h2UserTransactionsRepository.save(userTransactions);
    h2UserTransactionsRepository.updateUserTransaction(2, 1, "count");
    Optional<UserTransactions> optional =
        h2UserTransactionsRepository.selectIdUserTransaction(userTransactions);
    assertNotEquals(userTransactions.getCount(), optional.get().getCount());
    h2UserTransactionsRepository.deleteUserTransaction(userTransactions);
  }


  @Test
  public void whenDeleteUserTransactionIsWork() {
    Role role = new Role("User17");
    User user = new User("Arina", role);
    Market market = new Market("carShop", user);
    Item item = new Item("bmw", "auto", market, new BigDecimal(10000));
    UserTransactions userTransactions = new UserTransactions(user, item, 3, new BigDecimal(1100));
    h2UserTransactionsRepository.save(userTransactions);
    h2UserTransactionsRepository.deleteUserTransaction(userTransactions);
    Optional<UserTransactions> optional =
        h2UserTransactionsRepository.selectIdUserTransaction(userTransactions);
    assertTrue(optional.isEmpty());
  }

  @Test
  public void whenSelectNameAndIdUserTransactionIsWork() {
    Role role = new Role("User18");
    User user = new User("Sergey", role);
    Market market = new Market("Shop", user);
    Item item = new Item("mers", "auto", market, new BigDecimal(100000));
    UserTransactions userTransactions = new UserTransactions(user, item, 4, new BigDecimal(7100));
    h2UserTransactionsRepository.save(userTransactions);
    Optional<UserTransactions> optional =
        h2UserTransactionsRepository.selectIdUserTransaction(userTransactions);
    assertEquals(userTransactions.getPrice(), optional.get().getPrice());
  }

}





