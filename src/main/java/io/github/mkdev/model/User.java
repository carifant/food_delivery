package io.github.mkdev.model;

import java.util.UUID;

public class User {
  private UUID id;
  private String name;
  private Role role;

  /**
   * Create {@link io.github.mkdev.model.User} object with required fields.
   */
  public User(UUID id, String name, Role role) {
    this.id = id;
    this.name = name;
    this.role = role;
  }

  public User(String name, Role role) {
    this.name = name;
    this.role = role;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
