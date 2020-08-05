package io.github.mkdev.model;

import java.math.BigDecimal;
import java.util.UUID;

public class UserTransactions {
  private UUID id;
  private User user;
  private Item item;
  private Integer count;
  private BigDecimal price;
  private BigDecimal total;

  /**
   * Create {@link io.github.mkdev.model.UserTransactions} object with required fields.
   */
  public UserTransactions(UUID id, User user, Item item, Integer count, BigDecimal price) {
    this.id = id;
    this.user = user;
    this.item = item;
    this.count = count;
    this.price = price;
    this.total = this.price.multiply(BigDecimal.valueOf(this.count));
  }

  /**
   * Create {@link io.github.mkdev.model.Item} object with required fields.
   */

  public UserTransactions(User user, Item item, Integer count, BigDecimal price) {
    this.user = user;
    this.item = item;
    this.count = count;
    this.price = price;
    this.total = this.price.multiply(BigDecimal.valueOf(this.count));
  }

  public UUID getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}

