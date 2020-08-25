package io.github.mkdev;

import io.github.mkdev.dao.H2RoleRepository;
import io.github.mkdev.dao.HibernateBaseRepository;
import io.github.mkdev.database.HibernateUtilProperties;
import io.github.mkdev.model.Item;
import io.github.mkdev.model.Market;
import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import io.github.mkdev.model.UserTransactions;
import java.math.BigDecimal;
import java.util.Optional;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

  /**
   * Input program method. Allow start project code.
   */
  public static void main(String[] args) {
    Role r = new Role("Vasia");
    new H2RoleRepository().save(r);
    Optional<Role> role = new H2RoleRepository().selectNameAndIdRole(r);
    Role r2 = role.get();
    System.out.println(r2);


  }
}



