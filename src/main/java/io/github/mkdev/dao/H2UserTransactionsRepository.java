package io.github.mkdev.dao;

import io.github.mkdev.model.Role;
import io.github.mkdev.model.UserTransactions;
import java.sql.Connection;
import java.util.Optional;

public class H2UserTransactionsRepository extends H2BaseRepository implements Repository<UserTransactions> {

  public H2UserTransactionsRepository(Connection connection) {
    super(connection);
  }

  @Override
  public Optional<UserTransactions> save(
    UserTransactions userTransactions) {
    return Optional.empty();
  }
}
