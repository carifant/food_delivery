package io.github.mkdev.dao;

import io.github.mkdev.model.Item;
import io.github.mkdev.model.User;
import java.util.Optional;


public class H2UserRepository extends HibernateBaseRepository implements Repository<User> {

  @Override
  public Optional<User> save(User user) {
    create(user);
    return selectNameAndIdUser(user);
  }

  /**
   * Update program method. Allow to change the date.
   */

  public void updateUser(String newValue, String oldValue) {
    update("User", "name", newValue, oldValue);
  }

  /**
   * Delete program method. Allow to delete the date.
   */

  public void deleteUser(String oldValue) {
    delete("User", "name", oldValue);
  }

  /**
   * Select program method. Allow to get the date.
   */

  public Optional<User> selectNameAndIdUser(User user) {
    Optional<User> dbUser = Optional.empty();
    try {
      User result = (User) read("User", "id", user.getId());
      dbUser = Optional.of(new User(result.getId(),
        result.getName(), result.getRole()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dbUser;
  }
}

