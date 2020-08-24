package io.github.mkdev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mkdev.model.Role;
import io.github.mkdev.model.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class H2UserRepositoryTest {

  H2UserRepository h2UserRepository;

  @BeforeEach
  public void before() {
    h2UserRepository = new H2UserRepository();
  }

  @Test
  public void whenSaveIsWork() {
    Role role = new Role("user11");
    User user = new User("vasilisa", role);
    Optional<User> save = h2UserRepository.save(user);
    assertTrue(save.isPresent());
    h2UserRepository.deleteUser("vasia");
  }


  @Test
  public void whenUpdateUserIsWork() {
    Role role = new Role("User12");
    User user = new User("Lana", role);
    h2UserRepository.save(user);
    h2UserRepository.updateUser("Svetlana", "Lana");
    Optional<User> optional = h2UserRepository.selectNameAndIdUser(user);
    assertNotEquals(user.getName(), optional.get().getName());
    h2UserRepository.deleteUser("Svetlana");
  }

  @Test
  public void whenDeleteUserIsWork() {
    Role role = new Role("User13");
    User user = new User("Jana", role);
    h2UserRepository.save(user);
    h2UserRepository.deleteUser("Jana");
    Optional<User> optional = h2UserRepository.selectNameAndIdUser(user);
    assertTrue(optional.isEmpty());
  }

  @Test
  public void whenSelectNameAndIdUserIsWork() {
    Role role = new Role("User14");
    User user = new User("Victor", role);
    h2UserRepository.save(user);
    Optional<User> optional = h2UserRepository.selectNameAndIdUser(user);
    assertEquals(user.getName(), optional.get().getName());
  }
}



