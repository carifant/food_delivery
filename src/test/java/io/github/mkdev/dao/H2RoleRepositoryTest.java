package io.github.mkdev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mkdev.database.H2ConnectionManager;
import io.github.mkdev.model.Role;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class H2RoleRepositoryTest {

  public H2RoleRepository init() throws SQLException {
    H2ConnectionManager connectionManager = H2ConnectionManager.create();
    Connection connection = connectionManager.getConnection();
    return new H2RoleRepository(connection);
  }

  @Test
  public void whenSaveIsWork() {
    try {
      H2RoleRepository h2RoleRepository = init();
      Optional<Role> role = h2RoleRepository.save(new Role("ADMINp"));
      assertTrue(role.isPresent());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenUpdateRoleIsWork() {
    try {
      H2RoleRepository h2RoleRepository = init();
      h2RoleRepository.save(new Role("Lamer"));
      String id = h2RoleRepository.selectID("role", "name", "Lamer");
      h2RoleRepository.updateRole("'junior'", id);
      Map<String, String> map = h2RoleRepository.selectAll("role");
      String exp = "junior";
      assertEquals(exp, map.get(id));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenDeleteRoleIsWork() {
    try {
      H2RoleRepository h2RoleRepository = init();
      Role role = new Role("User5");
      h2RoleRepository.save(role);
      h2RoleRepository.deleteRole(role);
      String id2 = h2RoleRepository.selectID("role", "name", "User5");
      assertNull(id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenSelectNameAndIdRoleIsWork() {
    try {
      H2RoleRepository h2RoleRepository = init();
      Role role = new Role("UserOld");
      h2RoleRepository.save(role);
      Optional<Role> optional = h2RoleRepository.selectNameAndIdRole(role);
      String name = optional.get().getName();
      String exp = "UserOld";
      assertEquals(exp, name);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
