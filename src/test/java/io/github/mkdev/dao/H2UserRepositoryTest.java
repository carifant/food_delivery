package io.github.mkdev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mkdev.database.H2ConnectionManager;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class H2UserRepositoryTest {
  public H2UserRepository init() throws SQLException {
    H2ConnectionManager connectionManager = H2ConnectionManager.create();
    Connection connection = connectionManager.getConnection();
    return new H2UserRepository(connection);
  }

  @Test
  public void whenSaveIsWork() {
    try {
      H2UserRepository h2UserRepository = init();
      UUID uuid = UUID.randomUUID();
      Optional<User>
          user = h2UserRepository.save(new User("Vasilisa", new Role(uuid, "Userss")));
      assertTrue(user.isPresent());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenUpdateUserIsWork() {
    try {
      H2UserRepository h2UserRepository = init();
      UUID uuid = UUID.randomUUID();
      h2UserRepository.save(new User("Alisa", new Role(uuid, "Usert")));
      String id = h2UserRepository.selectID("users", "name", "Alisa");
      h2UserRepository.updateUser("'Alice'", id);
      Map<String, String> map = h2UserRepository.selectAll("users");
      String exp = "Alice";
      assertEquals(exp, map.get(id));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenDeleteUserIsWork() {
    try {
      H2UserRepository h2UserRepository = init();
      UUID uuid = UUID.randomUUID();
      User user = new User("Kate", new Role(uuid, "User21"));
      h2UserRepository.save(user);
      h2UserRepository.deleteUser(user);
      String id2 = h2UserRepository.selectID("users", "name", "Kate");
      assertNull(id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenSelectNameAndIdUserIsWork() {
    try {
      H2UserRepository h2UserRepository = init();
      UUID uuid = UUID.randomUUID();
      User user = new User("Debra", new Role(uuid, "User3"));
      h2UserRepository.save(user);
      Optional<User> optional = h2UserRepository.selectNameAndIdUser(user);
      String name = optional.get().getName();
      String exp = "Debra";
      assertEquals(exp, name);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}



