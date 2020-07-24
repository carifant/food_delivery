package io.github.mkdev.dao;

import static org.junit.jupiter.api.Assertions.*;


import io.github.mkdev.database.H2ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class H2BaseRepositoryTest {

  public H2BaseRepository init() throws SQLException {
    H2ConnectionManager connectionManager = H2ConnectionManager.create();
    Connection connection = connectionManager.getConnection();
    return new H2BaseRepository(connection);
  }

  @Test
  public void whenInsertIsWork() {
    try {
      H2BaseRepository h2BaseRepository = init();
      String query = "INSERT INTO ROLE(NAME) VALUES ('Vasiliy');";
      String id = h2BaseRepository.insert(query);
      assertNotNull(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenSelectAllFieldsIsWork() {
    try {
      H2BaseRepository h2BaseRepository = init();
      String query = "INSERT INTO ROLE(NAME) VALUES ('Alisa');";
      String id = h2BaseRepository.insert(query);
      Map<String, String> map = h2BaseRepository.selectAllFieldsByTableNameAndId("role", id);
      String exp = "Alisa";
      assertEquals(exp, map.get("name"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenSelectIDIsWork() {
    try {
      H2BaseRepository h2BaseRepository = init();
     String exp =  h2BaseRepository.insert("INSERT INTO ROLE(NAME) VALUES ('User7')");
     String id =  h2BaseRepository.selectID("role", "name", "User7");
     assertEquals(exp, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenSelectAllIsWork() {
    try {
      H2BaseRepository h2BaseRepository = init();
      h2BaseRepository.insert("INSERT INTO ROLE(NAME) VALUES ('User6')");
      Map<String, String> map = h2BaseRepository.selectAll("role");
      int size = 1;
      assertEquals(size, map.size());
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void whenSelectAllNotWork() {
    try {
      H2BaseRepository h2BaseRepository = init();
      h2BaseRepository.insert("INSERT INTO ROLE(NAME) VALUES ('ADMIN3')");
      String id = h2BaseRepository.selectID("role", "name", "ADMIN3");
      Map<String, String> map = h2BaseRepository.selectAll("role");
      Map<String, String> exp = new HashMap<>();
      exp.put(id, "ADMIN4");
      assertNotEquals(exp, map);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenUpdateIsWork() {
    try {
      H2BaseRepository h2BaseRepository = init();
      h2BaseRepository.insert("INSERT INTO ROLE(NAME) VALUES ('ADMINN')");
      String id = h2BaseRepository.selectID("role", "name", "ADMINN");
      h2BaseRepository.update("role", "name", "'Fedora'", id);
      Map<String, String> map = h2BaseRepository.selectAll("role");
      String exp = "Fedora";
      assertEquals(exp, map.get(id));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void whenUpdateNotWork() {
    try {
      H2BaseRepository h2BaseRepository = init();
      h2BaseRepository.insert("INSERT INTO ROLE(NAME) VALUES ('User2')");
      String id = h2BaseRepository.selectID("role", "name", "User2");
      h2BaseRepository.update("role", "name", "'Fedor'", id);
      Map<String, String> map = h2BaseRepository.selectAll("role");
      String exp = "User2";
      assertNotEquals(exp, map.get(id));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenDeleteIsWork() {
    try {
      H2BaseRepository h2BaseRepository = init();
      h2BaseRepository.insert("INSERT INTO ROLE(NAME) VALUES ('User5')");
      String id = h2BaseRepository.selectID("role", "name", "User5");
      h2BaseRepository.delete("role", "name", id);
      String id2 = h2BaseRepository.selectID("role", "name", "User5");
      assertNull(id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenDeleteNotWork() {
    try {
      H2BaseRepository h2BaseRepository = init();
      h2BaseRepository.insert("INSERT INTO ROLE(NAME) VALUES ('UserNew')");
      String id = h2BaseRepository.selectID("role", "name", "UserNew");
      h2BaseRepository.delete("role", "name", "11");
      String id2 = h2BaseRepository.selectID("role", "name", "UserNew");
      assertNotNull(id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}






