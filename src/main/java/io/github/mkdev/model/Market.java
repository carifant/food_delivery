package io.github.mkdev.model;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "market")
public class Market {
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private UUID id;
  @Column(name = "NAME")
  private String name;
  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User owner;

  /**
   * Create {@link io.github.mkdev.model.Market} object with required fields.
   */
  public Market() {

  }

  /**
   * Create {@link io.github.mkdev.model.Market} object with required fields.
   */
  public Market(UUID id, String name, User owner) {
    this.id = id;
    this.name = name;
    this.owner = owner;
  }

  public Market(String name) {
    this.name = name;
  }

  public Market(String name, User owner) {
    this.name = name;
    this.owner = owner;
  }

  @Override
  public String toString() {
    return "Market{"
      + "id=" + id
      + ", name='" + name + '\''
      + ", owner=" + owner
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

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}
