package io.github.mkdev.dao;

import io.github.mkdev.model.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class H2UserRepository extends H2BaseRepository implements Repository<User> {

  public H2UserRepository(Connection connection) {
    super(connection);
  }

  @Override
  public Optional<User> save(User user) {
    Optional<User> dbUser = Optional.empty();
    String query = "INSERT INTO USERS(NAME, ROLE_ID) VALUES ( '" + user.getName() + "', '"
        + user.getRole().getId() + "');";
    try {
      String id = this.insert(query);
      HashMap<String, String> userHash = this.selectAllFieldsByTableNameAndId("USERS", id);
      dbUser = Optional.of(new User(
        UUID.fromString(userHash.get("id")),
        userHash.get("name"), user.getRole()
      ));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return dbUser;
  }

  /**
   * Update program method. Allow to change the date.
   */

  public void updateUser(String newName, String id) {
    try {
      this.update("Users", "name", newName, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Delete program method. Allow to delete the date.
   */

  public void deleteUser(User user) {
    try {
      String id = this.selectID("users", "name", user.getName());
      this.delete("Users", "name", id);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (NullPointerException x) {
      x.printStackTrace();
      System.out.println("Данного объекта не существует");
    }
  }

  /**
   * Select program method. Allow to select the date.
   */

  public Optional<User> selectNameAndIdUser(User user) {
    Optional<User> dbUser = Optional.empty();
    try {
      HashMap<String, String> userHash = this
          .selectAllFieldsByTableNameAndId("users", this.selectID("users", "name", user.getName()));
      dbUser = Optional.of(new User(
        UUID.fromString(userHash.get("id")),
        userHash.get("name"), user.getRole()
      ));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dbUser;
  }
}

