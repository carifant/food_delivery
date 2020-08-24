package io.github.mkdev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mkdev.model.Role;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class H2RoleRepositoryTest {

  H2RoleRepository h2RoleRepository;

  @BeforeEach
  public void before() {
    h2RoleRepository = new H2RoleRepository();
  }

  @Test
  public void whenSaveIsWork() {
    Role role = new Role("user9");
    Optional<Role> optional = h2RoleRepository.save(role);
    assertTrue(optional.isPresent());
  }


  @Test
  public void whenUpdateRoleIsWork() {
    Role role = new Role("user10");
    h2RoleRepository.save(role);
    h2RoleRepository.updateRole("NOtNew34", "user10");
    Optional<Role> optional = h2RoleRepository.selectNameAndIdRole(role);
    assertNotEquals(role.getName(), optional.get().getName());
  }


  @Test
  public void whenDeleteRoleIsWork() {
    Role role = new Role("Oleg");
    h2RoleRepository.save(role);
    h2RoleRepository.deleteRole("Oleg");
    Optional<Role> optional = h2RoleRepository.selectNameAndIdRole(role);
    assertTrue(optional.isEmpty());
  }

  @Test
  public void whenSelectNameAndIdRoleIsWork() {
    Role role = new Role("Anna");
    h2RoleRepository.save(role);
    Optional<Role> optional = h2RoleRepository.selectNameAndIdRole(role);
    assertEquals(role.getName(), optional.get().getName());
  }
}

