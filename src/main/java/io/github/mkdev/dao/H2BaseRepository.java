package io.github.mkdev.dao;

import io.github.mkdev.model.Role;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class H2BaseRepository {
  private Connection connection;


  public H2BaseRepository(Connection connection) {
    this.connection = connection;
  }


  /**
   * Execute SQL query for insert new record.
   *
   * @param insertQuery sql query
   * @return id of inserted row
   * @throws SQLException when some error in inserting query.
   */
  public String insert(String insertQuery) throws SQLException {
    String id = null;


    String[] returnedAttributes = {"id"};
    try (PreparedStatement insertStatement
           = connection.prepareStatement(insertQuery, returnedAttributes)) {
      int rows = insertStatement.executeUpdate();
      if (rows == 0) {
        throw new SQLException("Failed of insertion");
      }
      try (ResultSet rs = insertStatement.getGeneratedKeys()) {
        if (rs.next()) {
          id = rs.getString("id");
        } else {
          throw new SQLException("Failed of insertion");
        }
      }
    }


    return id;
  }


  /**
   * Select all field from table by name and row id.
   *
   * @param tableName name of table
   * @param id        row id
   * @return {@link java.util.HashMap} with k/v table representation
   * @throws SQLException if can't retrieve field.
   */

  public HashMap<String, String> selectAllFieldsByTableNameAndId(String tableName,
                                                                 String id) throws SQLException {
    HashMap<String, String> result = new HashMap<>();
    Statement statementx = connection.createStatement();
    ResultSet selectStatement = statementx.executeQuery("SELECT * " + "FROM "
        + tableName.toUpperCase(
        Locale.ENGLISH) + " WHERE ID = '" + id + "';");
    while (selectStatement.next()) {
      Class<?> cls = Role.class;
      Field[] fieldlist = cls.getDeclaredFields();
      for (Field field : fieldlist) {
        result.put(field.getName(), selectStatement.getString(field.getName()));
      }
    }
    return result;
  }

  /**
   * Update program method. Allow to change the date.
   */

  public void update(String tableName, String nameOfColumn, String newDate, String id)
      throws SQLException {
    Statement statementx = connection.createStatement();
    statementx.executeUpdate(
        "UPDATE " + tableName.toUpperCase(Locale.ENGLISH) + " SET " + nameOfColumn + " = " + newDate
        + " WHERE ID ='" + id + "';"
    );
  }

  /**
   * Delete program method. Allow to delit the date.
   */

  public void delete(String tableName, String nameOfColumn, String id)
      throws SQLException {
    Statement statementx = connection.createStatement();
    statementx.executeUpdate("DELETE FROM " + tableName.toUpperCase(Locale.ENGLISH) + " "
        + nameOfColumn + " WHERE ID ='" + id + "';"
    );
  }

  /**
   * Selecte program method. Allow to select the date.
   */

  public Map<String, String> selectAll(String tableName)
      throws SQLException {
    Map<String, String> map = new HashMap<>();
    Statement statementx = connection.createStatement();
    ResultSet rs =
        statementx.executeQuery("SELECT * FROM " + tableName.toUpperCase(Locale.ENGLISH));
    while (rs.next()) {
      map.put(rs.getString(1), rs.getString(2));
      map.put(rs.getString(3), rs.getString(4));
      map.put(rs.getString(5), rs.getString(6));
    }
    return map;
  }

  /**
   * SelectID program method. Allow to get the ID.
   */

  public String selectID(String tableName, String tableColumn, String query)
      throws SQLException {

    Statement statementx = connection.createStatement();
    ResultSet rs = statementx.executeQuery(
        "SELECT id FROM " + tableName.toUpperCase(Locale.ENGLISH)
        + " WHERE " + tableColumn + " = '"
        + query + "'");
    String result = null;
    if (rs.next()) {
      result = rs.getString(1);
    }
    return result;
  }
}



