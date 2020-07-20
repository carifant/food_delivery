package io.github.mkdev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.mkdev.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoleServiceTest {

  @Test
  public void whenRoleAdminWasCreated() {
    RoleService roleService = new RoleService();
    Role role = roleService.createAdminRole();
    String name = "ADMIN";
    assertEquals(role.getName(), name);
  }

  @Test
  public void whenRoleAdminNotCreated() {
    RoleService roleService = null;
    Assertions.assertThrows(NullPointerException.class, () -> {
      roleService.createAdminRole();
    });
  }

  @Test
  public void whenRoleUserWasCreated() {
    RoleService roleService = new RoleService();
    Role role = roleService.createUserRole();
    String name = "USER";
    assertEquals(role.getName(), name);
  }

  @Test
  public void whenRoleUserNotCreated() {
    RoleService roleService = null;
    Assertions.assertThrows(NullPointerException.class, () -> {
      roleService.createUserRole();
    });
  }
}
