package io.github.mkdev.dao;

import io.github.mkdev.model.Item;
import java.sql.Connection;
import java.util.Optional;

public class H2ItemRepository extends H2BaseRepository implements Repository<Item> {

  public H2ItemRepository(Connection connection) {
    super(connection);
  }

  @Override
  public Optional<Item> save(Item item) {
    return Optional.empty();
  }
}
