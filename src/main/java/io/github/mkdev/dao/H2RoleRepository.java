package io.github.mkdev.dao;

import io.github.mkdev.model.Role;
import java.util.Optional;


public class H2RoleRepository extends HibernateBaseRepository implements Repository<Role> {

  @Override
  public Optional<Role> save(Role role) {

    create(role);
    return selectNameAndIdRole(role);
  }

  /**
   * Update program method. Allow to change the date.
   */

  public void updateRole(String newValue, String oldValue) {
    update("Role", "name", newValue, oldValue);
  }

  /**
   * Delete program method. Allow to delete the date.
   */

  public void deleteRole(String oldValue) {
    delete("Role", "name", oldValue);
  }


  /**
   * Select program method. Allow to get the date.
   */

  public Optional<Role> selectNameAndIdRole(Role role) {
    Optional<Role> dbRole = Optional.empty();
    try {
      Role result = (Role) read("Role", "id", role.getId());
      dbRole = Optional.of(new Role(result.getId(),
        result.getName()));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return dbRole;
  }
}
