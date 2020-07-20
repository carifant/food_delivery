package io.github.mkdev.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemServiceTest {

  @Test
  public void whenItemWasCreated() {
    ItemService itemService = new ItemService();
    Role role = new Role("Admin");
    User user = new User("User", role);
    Market market = new Market("market", user);
    BigDecimal bigDecimal = new BigDecimal(10);
    Item item = itemService.createItem("Service", "Phone", market, bigDecimal);
    assertNotNull(item);
  }

  @Test
  public void whenItemNotCreated() {
    ItemService itemService = null;
    Role role = new Role("Admin");
    User user = new User("User", role);
    Market market = new Market("market", user);
    BigDecimal bigDecimal = new BigDecimal(10);
    Assertions.assertThrows(NullPointerException.class, () -> {
      itemService.createItem("Service", "Phone", market, bigDecimal);
    });
  }
}
