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

  public void updateRole(String newName, String id) {
    try {
      this.update("ROLE", "name", newName, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteRole(String id) {
    try {
      this.delete("ROLE", "name", id);
    } catch (SQLException e) {
      System.out.println("Данного объекта не существует");
      e.printStackTrace();
    } catch (NullPointerException x) {
      x.printStackTrace();
      System.out.println("Данного объекта не существует");
    }
  }

  public Optional<Role> selectNameAndIdRole(Role role) {
    Optional<Role> dbRole = Optional.empty();
    try {
      HashMap<String, String> roleHash =
        this.selectAllFieldsByTableNameAndId("ROLE", role.getId().toString());
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
