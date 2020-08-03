package io.github.mkdev.dao;

import io.github.mkdev.model.Item;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class H2ItemRepository extends H2BaseRepository implements Repository<Item> {

  public H2ItemRepository(Connection connection) {
    super(connection);
  }

  @Override
  public Optional<Item> save(Item item) {
    Optional<Item> dbItem = Optional.empty();
    String query =
        "INSERT INTO ITEMS(NAME, DESCRIPTION, MARKET_ID, PRICE) VALUES ( '" + item.getName()
          + "', '" + item.getDescription() + "', '" + item.getMarket().getId() + "', '"
          + item.getPrice() + "');";
    try {
      String id = this.insert(query);
      HashMap<String, String> itemHash = this.selectAllFieldsByTableNameAndId("ITEMS", id);
      dbItem = Optional.of(new Item(
        UUID.fromString(itemHash.get("id")),
        itemHash.get("name"), itemHash.get("description"), item.getMarket(), item.getPrice()
      ));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return dbItem;
  }

  /**
   * Update program method. Allow to change the date.
   */

  public void updateItem(String newName, String id, String nameOfColumn) {
    try {
      this.update("Items", nameOfColumn, newName, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Delete program method. Allow to delite the date.
   */

  public void deleteItem(Item item) {
    try {
      String id = this.selectID("items", "name", item.getName());
      this.delete("items", "name", id);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (NullPointerException x) {
      x.printStackTrace();
      System.out.println("Данного объекта не существует");
    }
  }

  /**
   * Select program method. Allow to get name and id.
   */

  public Optional<Item> selectNameAndIdItem(Item item) {
    Optional<Item> dbItem = Optional.empty();
    try {
      HashMap<String, String> itemHash = this
          .selectAllFieldsByTableNameAndId("items", this.selectID("items",
          "name", item.getName()));
      dbItem = Optional.of(new Item(
        UUID.fromString(itemHash.get("id")),
        itemHash.get("name"), itemHash.get("description"), item.getMarket(), item.getPrice()
      ));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dbItem;
  }
}

