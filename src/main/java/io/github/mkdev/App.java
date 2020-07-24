package io.github.mkdev;

import io.github.mkdev.dao.H2RoleRepository;
import io.github.mkdev.dao.Repository;
import io.github.mkdev.database.H2ConnectionManager;
import io.github.mkdev.model.Role;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;


/**
 * Input program method. Allow start project code.
 */
public class App {

  public static void main(String[] args) throws SQLException {
    H2ConnectionManager connectionManager = H2ConnectionManager.create();
    Connection connection = connectionManager.getConnection();
    Repository<Role> h2RoleRepository = new H2RoleRepository(connection);
    Optional<Role> role = h2RoleRepository.save(new Role("ADMIN"));
    System.out.println(role.get().getName());
  }
}
