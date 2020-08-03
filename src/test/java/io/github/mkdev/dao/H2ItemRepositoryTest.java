package io.github.mkdev.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mkdev.database.H2ConnectionManager;
import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class H2ItemRepositoryTest {

  public H2ItemRepository init() throws SQLException {
    H2ConnectionManager connectionManager = H2ConnectionManager.create();
    Connection connection = connectionManager.getConnection();
    return new H2ItemRepository(connection);
  }

  @Test
  public void whenSaveIsWork() {
    try {
      H2ItemRepository h2ItemRepository = init();
      UUID uuid = UUID.randomUUID();
      BigDecimal bigDecimal = new BigDecimal("600.55");
      Optional<Item> user = h2ItemRepository.save(new Item("phone", "mobile phone",
          new Market(uuid, "shop", null), bigDecimal));
      assertTrue(user.isPresent());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenUpdateItemIsWork() {
    try {
      H2ItemRepository h2ItemRepository = init();
      UUID uuid = UUID.randomUUID();
      BigDecimal bigDecimal = new BigDecimal("76600.55");
      h2ItemRepository.save(new Item("car", "audi", new Market(uuid, "shopCar", null), bigDecimal));
      String id = h2ItemRepository.selectID("items", "name", "car");
      h2ItemRepository.updateItem("'q8'", id, "name");
      Map<String, String> map = h2ItemRepository.selectAll("items");
      String exp = "q8";
      assertEquals(exp, map.get(id));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenDeleteItemIsWork() {
    try {
      H2ItemRepository h2ItemRepository = init();
      UUID uuid = UUID.randomUUID();
      BigDecimal bigDecimal = new BigDecimal("12.2");
      Item item = new Item("fork", "simple_fork", new Market(uuid, "market", null), bigDecimal);
      h2ItemRepository.save(item);
      h2ItemRepository.deleteItem(item);
      String id2 = h2ItemRepository.selectID("items", "name", "fork");
      assertNull(id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenSelectNameAndIdUserIsWork() {
    try {
      H2ItemRepository h2ItemRepository = init();
      UUID uuid = UUID.randomUUID();
      BigDecimal bigDecimal = new BigDecimal("2500");
      Item item = new Item("watch", "head_watch", new Market(uuid, "clock shop", null), bigDecimal);
      h2ItemRepository.save(item);
      Optional<Item> optional = h2ItemRepository.selectNameAndIdItem(item);
      String name = optional.get().getName();
      String exp = "watch";
      assertEquals(exp, name);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}


