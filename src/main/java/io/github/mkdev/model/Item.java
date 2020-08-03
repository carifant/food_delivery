package io.github.mkdev.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Item {
  private UUID id;
  private String name;
  private String description;
  private Market market;
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

