package io.github.mkdev.dao;

import io.github.mkdev.model.Role;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;


public class H2RoleRepository extends H2BaseRepository implements Repository<Role> {


  public H2RoleRepository(Connection connection) {
    super(connection);
  }


  @Override
  public Optional<Role> save(Role role) {
    Optional<Role> dbRole = Optional.empty();
    String query = "INSERT INTO ROLE(NAME) VALUES ( '" + role.getName() + "' );";
    try {
      String id = this.insert(query);
      HashMap<String, String> roleHash = this.selectAllFieldsByTableNameAndId("ROLE", id);
      dbRole = Optional.of(new Role(
        UUID.fromString(roleHash.get("id")),
        roleHash.get("name")
      ));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return dbRole;
  }

  /**
   * Update program method. Allow to change the date.
   */

  public void updateRole(String newName, String id) {
    try {
      this.update("ROLE", "name", newName, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Delete program method. Allow to delete the date.
   */

  public void deleteRole(Role role) {
    try {
      String id = this.selectID("role", "name", role.getName());
      this.delete("ROLE", "name", id);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (NullPointerException x) {
      x.printStackTrace();
      System.out.println("Данного объекта не существует");
    }
  }

  /**
   * Select program method. Allow to get the date.
   */

  public Optional<Role> selectNameAndIdRole(Role role) {
    Optional<Role> dbRole = Optional.empty();
    try {
      HashMap<String, String> roleHash =
          this.selectAllFieldsByTableNameAndId("ROLE", this.selectID(
            "role", "name", role.getName()));
      dbRole = Optional.of(new Role(
        UUID.fromString(roleHash.get("id")),
        roleHash.get("name")
      ));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dbRole;
  }
}
