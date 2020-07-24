package io.github.mkdev.dao;

import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import java.sql.Connection;
import java.util.Optional;

public class H2MarketRepository extends H2BaseRepository implements Repository<Market> {

  public H2MarketRepository(Connection connection) {
    super(connection);
  }

  @Override
  public Optional<Market> save(Market market) {
    return Optional.empty();
  }
}
