package io.github.mkdev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class H2ItemRepositoryTest {

  H2ItemRepository h2ItemRepository;

  @BeforeEach
  public void before() {
    h2ItemRepository = new H2ItemRepository();
  }

  @Test
  public void whenSaveIsWork() {
    Role role = new Role("user");
    User user = new User("vasia", role);
    Market market = new Market("dns", user);
    Item item = new Item("Iphone", "smartphone", market, new BigDecimal(1000));
    Optional<Item> save = h2ItemRepository.save(item);
    assertTrue(save.isPresent());
    h2ItemRepository.deleteItem("Iphone");
  }


  @Test
  public void whenUpdateItemIsWork() {
    Role role = new Role("user2");
    User user = new User("elena", role);
    Market market = new Market("dns2", user);
    Item item = new Item("Samsung", "smartphone", market, new BigDecimal(500));
    h2ItemRepository.save(item);
    h2ItemRepository.updateItem("Motorola", "Samsung");
    Optional<Item> optional = h2ItemRepository.selectNameAndIdItem(item);
    assertNotEquals(item.getName(), optional.get().getName());
    h2ItemRepository.deleteItem("Motorola");
  }


  @Test
  public void whenDeleteItemIsWork() {
    Role role = new Role("user3");
    User user = new User("Petya", role);
    Market market = new Market("Spar", user);
    Item item = new Item("HTC", "smartphone", market, new BigDecimal(400));
    h2ItemRepository.save(item);
    h2ItemRepository.deleteItem("HTC");
    Optional<Item> optional = h2ItemRepository.selectNameAndIdItem(item);
    assertTrue(optional.isEmpty());
  }

  @Test
  public void whenSelectNameAndIdItemIsWork() {
    Role role = new Role("user4");
    User user = new User("Sveta", role);
    Market market = new Market("Ok", user);
    Item item = new Item("Alcatel", "smartphone", market, new BigDecimal(300));
    h2ItemRepository.save(item);
    Optional<Item> optional = h2ItemRepository.selectNameAndIdItem(item);
    assertEquals(item.getName(), optional.get().getName());
  }
}


