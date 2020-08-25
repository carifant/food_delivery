package io.github.mkdev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class H2MarketRepositoryTest {

  H2MarketRepository h2MarketRepository;

  @BeforeEach
  public void  before() {
    h2MarketRepository = new H2MarketRepository();
  }

  @Test
  public void whenSaveIsWork() {
    Role role = new Role("user5");
    User user = new User("avasia", role);
    Market market = new Market("dns44", user);
    Optional<Market> save = h2MarketRepository.save(market);
    assertTrue(save.isPresent());
    h2MarketRepository.deleteMarket("dns44");
  }


  @Test
  public void whenUpdateMarketIsWork() {
    Role role = new Role("user6");
    User user = new User("velena", role);
    Market market = new Market("dns22", user);
    h2MarketRepository.save(market);
    h2MarketRepository.updateMarket("Sitilink", "dns22");
    Optional<Market> optional = h2MarketRepository.selectNameAndIdMarket(market);
    assertNotEquals(market.getName(), optional.get().getName());
    h2MarketRepository.deleteMarket("dns22");
  }


  @Test
  public void whenDeleteMarketIsWork() {
    Role role = new Role("user7");
    User user = new User("Petrarka", role);
    Market market = new Market("Spar2", user);
    h2MarketRepository.save(market);
    h2MarketRepository.deleteMarket("Spar2");
    Optional<Market> optional = h2MarketRepository.selectNameAndIdMarket(market);
    assertTrue(optional.isEmpty());
  }

  @Test
  public void whenSelectNameAndIdMarketIsWork() {
    Role role = new Role("user8");
    User user = new User("Ada", role);
    Market market = new Market("Obi", user);
    h2MarketRepository.save(market);
    Optional<Market> optional = h2MarketRepository.selectNameAndIdMarket(market);
    assertEquals(market.getName(), optional.get().getName());
  }
}



