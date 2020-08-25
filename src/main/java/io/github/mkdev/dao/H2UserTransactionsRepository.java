package io.github.mkdev.dao;

import io.github.mkdev.model.UserTransactions;
import java.util.Optional;

public class H2UserTransactionsRepository extends HibernateBaseRepository
    implements Repository<UserTransactions> {

  @Override
  public Optional<UserTransactions> save(UserTransactions userTransactions) {
    create(userTransactions);
    return selectIdUserTransaction(userTransactions);
  }

  /**
   * Update program method. Allow to change the date.
   */

  public void updateUserTransaction(int newValue, int oldValue, String column) {
    update("UserTransactions", column, newValue, oldValue);
  }

  /**
   * Delete program method. Allow to delete the date.
   */

  public void deleteUserTransaction(UserTransactions userTransactions) {
    delete("UserTransactions", "id", userTransactions.getId());
  }

  /**
   * Select program method. Allow to get the date.
   */

  public Optional<UserTransactions> selectIdUserTransaction(UserTransactions userTransactions) {
    Optional<UserTransactions> dbUserTransactions = Optional.empty();
    try {
      UserTransactions result =
          (UserTransactions) read("UserTransactions", "id", userTransactions.getId());
      dbUserTransactions = Optional.of(new UserTransactions(result.getId(),
        result.getUser(), result.getItem(), result.getCount(), result.getPrice()));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return dbUserTransactions;
  }
}



