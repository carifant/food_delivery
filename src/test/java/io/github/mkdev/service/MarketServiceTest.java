package io.github.mkdev.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MarketServiceTest {

  @Test
  public void whenMarketWasCreated() {
    Role role = new Role("Admin");
    User user = new User("User", role);
    MarketService marketService = new MarketService();
    Market market = marketService.createMarket("market", user);
    assertNotNull(market);
  }

  @Test
  public void whenMarketNotCreated() {
    Role role = new Role("Admin");
    User user = new User("User", role);
    MarketService marketService = null;
    Assertions.assertThrows(NullPointerException.class, () -> {
      marketService.createMarket("market", user);
    });
  }
}
