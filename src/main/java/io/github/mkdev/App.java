package io.github.mkdev;

import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import io.github.mkdev.service.RoleService;
import io.github.mkdev.service.UserService;

public class App {

  /**
   * Input program method. Allow start project code.
   */
  public static void main(String[] args) {
    RoleService roleService = new RoleService();
    UserService userService = new UserService();
    Role adminRole = roleService.createAdminRole();
    Role userRole = roleService.createUserRole();
    User admin = userService.create("admin", adminRole);
    User user = userService.create("user", userRole);
    System.out.println("Create admin with name: " + admin.getName());
    System.out.println("Create admin with name: " + user.getName());
  }
}
