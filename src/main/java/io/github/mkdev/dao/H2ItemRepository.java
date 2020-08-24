package io.github.mkdev.dao;

import io.github.mkdev.model.Item;
import java.util.Optional;

public class H2ItemRepository extends HibernateBaseRepository implements Repository<Item> {

  @Override
  public Optional<Item> save(Item item) {
    create(item);
    return selectNameAndIdItem(item);
  }

  /**
   * Update program method. Allow to change the date.
   */

  public void updateItem(String newValue, String oldValue) {
    update("Item", "name", newValue, oldValue);
  }

  /**
   * Delete program method. Allow to delete the date.
   */

  public void deleteItem(String oldValue) {
    delete("Item", "name", oldValue);
  }

  /**
   * Select program method. Allow to get the date.
   */

  public Optional<Item> selectNameAndIdItem(Item item) {
    Optional<Item> dbItem = Optional.empty();
    try {
      Item result = (Item) read("Item", "id", item.getId());
      dbItem = Optional.of(new Item(result.getId(),
        result.getName(), result.getDescription(), result.getMarket(), result.getPrice()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dbItem;
  }
}

