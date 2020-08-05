package io.github.mkdev.dao;

import io.github.mkdev.model.Market;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class H2MarketRepository extends H2BaseRepository implements Repository<Market> {

  public H2MarketRepository(Connection connection) {
    super(connection);
  }

  @Override
  public Optional<Market> save(Market market) {
    Optional<Market> dbMarket = Optional.empty();
    String query = "INSERT INTO MARKET(NAME, USER_ID) VALUES ( '" + market.getName() + "', '"
        + market.getOwner().getId() + "');";
    try {
      String id = this.insert(query);
      HashMap<String, String> marketHash = this.selectAllFieldsByTableNameAndId("MARKET", id);
      dbMarket = Optional.of(new Market(
        UUID.fromString(marketHash.get("id")),
        marketHash.get("name"), market.getOwner()
      ));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return dbMarket;
  }
  /**
   * Update program method. Allow to change the date.
   */

  public void updateMarket(String newName, String id) {
    try {
      this.update("Market", "name", newName, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Delete program method. Allow to delete the date.
   */

  public void deleteMarket(Market market) {
    try {
      String id = this.selectID("market", "name", market.getName());
      this.delete("MARKET", "name", id);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (NullPointerException x) {
      x.printStackTrace();
      System.out.println("Данного объекта не существует");
    }
  }

  /**
   * Select program method. Allow to get the date.
   */

  public Optional<Market> selectNameAndIdMarket(Market market) {
    Optional<Market> dbMarket = Optional.empty();
    try {
      HashMap<String, String> marketHash = this.selectAllFieldsByTableNameAndId("MARKET",
          this.selectID("market", "name", market.getName()));
      dbMarket = Optional.of(new Market(
        UUID.fromString(marketHash.get("id")),
        marketHash.get("name"), market.getOwner()
      ));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dbMarket;
  }
}


