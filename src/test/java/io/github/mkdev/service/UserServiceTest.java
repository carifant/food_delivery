package io.github.mkdev.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserServiceTest {

  @Test
  public void whenUserWasCreated() {
    UserService userService = new UserService();
    Role role = new Role("Admin");
    User user = userService.create("Vasia", role);
    assertNotNull(user);
  }

  @Test
  public void whenUserNotCreated() {
    UserService userService = null;
    Role role = new Role("Admin");
    Assertions.assertThrows(NullPointerException.class, () -> {
      userService.create("Vasia", role);
    });
  }
}
