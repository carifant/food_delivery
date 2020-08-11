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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_transaction")
public class UserTransactions {
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private UUID id;
  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;
  @OneToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "item_id")
  private Item item;
  @Column(name = "count")
  private Integer count;
  @Column(name = "price")
  private BigDecimal price;
  @Column(name = "total")
  private BigDecimal total;

  public UserTransactions() {
  }

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

  @Override
  public String toString() {
    return "UserTransactions{"
      + "id=" + id
      + ", user=" + user
      + ", item=" + item
      + ", count=" + count
      + ", price=" + price
      + ", total=" + total
      + '}';
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

