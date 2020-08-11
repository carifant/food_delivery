package io.github.mkdev.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private UUID id;
  @Column(name = "NAME")
  private String name;

  public Role() {
  }

  /**
   * Create {@link io.github.mkdev.model.Role} object with required fields.
   */
  public Role(String name) {
    this.name = name;
  }

  public Role(UUID id, String name) {
    this.id = id;
    this.name = name;
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

  @Override
  public String toString() {
    return "Role{"
      + "id=" + id
      + ", name='" + name + '\''
      + '}';
  }
}
