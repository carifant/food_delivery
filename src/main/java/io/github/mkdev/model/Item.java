package io.github.mkdev.model;

import java.math.BigDecimal;
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
@Table(name = "items")
public class Item {
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private UUID id;
  @Column(name = "NAME")
  private String name;
  @Column(name = "description")
  private String description;
  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "market_id")
  private Market market;
  @Column(name = "price")
  private BigDecimal price;

  /**
   * Create {@link io.github.mkdev.model.Item} object with required fields.
   */
  public Item(String name, String description, Market market, BigDecimal price) {
    this.name = name;
    this.description = description;
    this.market = market;
    this.price = price;
  }

  /**
   * Create {@link io.github.mkdev.model.Item} object with required fields.
   */

  public Item(UUID id, String name, String description, Market market, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.market = market;
    this.price = price;
  }

  public Item() {
  }

  @Override
  public String toString() {
    return "Item{"
      + "id=" + id
      + ", name='" + name + '\''
      + ", description='" + description + '\''
      + ", market=" + market
      + ", price=" + price
      + '}';
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Market getMarket() {
    return market;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setMarket(Market market) {
    this.market = market;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}

