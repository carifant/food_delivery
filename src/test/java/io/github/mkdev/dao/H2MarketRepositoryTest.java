package io.github.mkdev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mkdev.database.H2ConnectionManager;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class H2MarketRepositoryTest {

  public H2MarketRepository init() throws SQLException {
    H2ConnectionManager connectionManager = H2ConnectionManager.create();
    Connection connection = connectionManager.getConnection();
    return new H2MarketRepository(connection);
  }

  @Test
  public void whenSaveIsWork() {
    try {
      H2MarketRepository h2MarketRepository = init();
      UUID uuid = UUID.randomUUID();
      Optional<Market> market = h2MarketRepository
          .save(new Market("MarketPlace", new User(uuid, "Userm", null)));
      assertTrue(market.isPresent());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenUpdateMarketIsWork() {
    try {
      H2MarketRepository h2MarketRepository = init();
      UUID uuid = UUID.randomUUID();
      h2MarketRepository.save(new Market("SHOP", new User(uuid, "Userb", null)));
      String id = h2MarketRepository.selectID("market", "name", "SHOP");
      h2MarketRepository.updateMarket("'Spar'", id);
      Map<String, String> map = h2MarketRepository.selectAll("market");
      String exp = "Spar";
      assertEquals(exp, map.get(id));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenDeleteRoleIsWork() {
    try {
      H2MarketRepository h2MarketRepository = init();
      UUID uuid = UUID.randomUUID();
      Market market = new Market("Sitilink", new User(uuid, "User3h", null));
      h2MarketRepository.save(market);
      h2MarketRepository.deleteMarket(market);
      String id2 = h2MarketRepository.selectID("market", "name", "Sitilink");
      assertNull(id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenSelectNameAndIdRoleIsWork() {
    try {
      H2MarketRepository h2MarketRepository = init();
      UUID uuid = UUID.randomUUID();
      Market market = new Market("Letyal", new User(uuid, "Userr4", null));
      h2MarketRepository.save(market);
      Optional<Market> optional = h2MarketRepository.selectNameAndIdMarket(market);
      String name = optional.get().getName();
      String exp = "Letyal";
      assertEquals(exp, name);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}



