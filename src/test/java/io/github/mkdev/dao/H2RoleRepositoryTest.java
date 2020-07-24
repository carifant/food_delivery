package io.github.mkdev.dao;


import static org.junit.jupiter.api.Assertions.*;

import io.github.mkdev.database.H2ConnectionManager;
import io.github.mkdev.model.Role;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class H2RoleRepositoryTest {

  @Test
  public void whenSaveIsWork() {
    try {
      H2ConnectionManager connectionManager = H2ConnectionManager.create();
      Connection connection = connectionManager.getConnection();
      Repository<Role> h2RoleRepository = new H2RoleRepository(connection);
      Optional<Role> role = h2RoleRepository.save(new Role("ADMIN"));
      assertTrue(role.isPresent());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenUpdateRoleIsWork() {
    try {
      H2ConnectionManager connectionManager = H2ConnectionManager.create();
      Connection connection = connectionManager.getConnection();
      H2RoleRepository h2RoleRepository = new H2RoleRepository(connection);
      h2RoleRepository.save(new Role("ADMINN"));
      String id = h2RoleRepository.selectID("role", "name", "ADMINN");
      h2RoleRepository.updateRole("'Fedora'", id);
      Map<String, String> map = h2RoleRepository.selectAll("role");
      String exp = "Fedora";
      assertEquals(exp, map.get(id));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



  @Test
  public void whenUpdateNotWork() {
    try {
      H2ConnectionManager connectionManager = H2ConnectionManager.create();
      Connection connection = connectionManager.getConnection();
      H2RoleRepository h2RoleRepository = new H2RoleRepository(connection);
      h2RoleRepository.save(new Role("User2"));
      String id = h2RoleRepository.selectID("role", "name", "User2");
      h2RoleRepository.updateRole("'Fedor'", id);
      Map<String, String> map = h2RoleRepository.selectAll("role");
      String exp = "User2";
      assertNotEquals(exp, map.get(id));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



  @Test
  public void whenDeleteIsWork() {
    try {
      H2ConnectionManager connectionManager = H2ConnectionManager.create();
      Connection connection = connectionManager.getConnection();
      H2RoleRepository h2RoleRepository = new H2RoleRepository(connection);
      h2RoleRepository.save(new Role("User5"));
      String id = h2RoleRepository.selectID("role", "name", "User5");
      h2RoleRepository.deleteRole(id);
      String id2 = h2RoleRepository.selectID("role", "name", "User5");
      assertNull(id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



  @Test
  public void whenDeleteNotWork() {
    try {
      H2ConnectionManager connectionManager = H2ConnectionManager.create();
      Connection connection = connectionManager.getConnection();
      H2RoleRepository h2RoleRepository = new H2RoleRepository(connection);
      h2RoleRepository.save(new Role("UserNew"));
      String id = h2RoleRepository.selectID("role", "name", "UserNew");
      h2RoleRepository.deleteRole("11");
      String id2 = h2RoleRepository.selectID("role", "name", "UserNew");
      assertNotNull(id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



}
