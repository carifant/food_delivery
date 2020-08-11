package io.github.mkdev.model;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private UUID id;

  @Column(name = "NAME")
  private String name;

  @OneToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "role_id")
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

  public User() {

  }

  @Override
  public String toString() {
    return "User{"
      + "id=" + id
      + ", name='" + name + '\''
      + ", role=" + role
      + '}';
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
