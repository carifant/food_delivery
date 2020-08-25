package io.github.mkdev.dao;

import io.github.mkdev.model.Market;
import java.util.Optional;


public class H2MarketRepository extends HibernateBaseRepository implements Repository<Market> {

  @Override
  public Optional<Market> save(Market market) {
    create(market);
    return selectNameAndIdMarket(market);
  }

  /**
   * Update program method. Allow to change the date.
   */

  public void updateMarket(String newValue, String oldValue) {
    update("Market", "name", newValue,  oldValue);
  }

  /**
   * Delete program method. Allow to delete the date.
   */

  public void deleteMarket(String oldValue) {
    delete("Market", "name", oldValue);
  }

  /**
   * Select program method. Allow to get the date.
   */

  public Optional<Market> selectNameAndIdMarket(Market market) {
    Optional<Market> dbMarket = Optional.empty();
    try {
      Market result = (Market) read("Market", "id", market.getId());
      dbMarket = Optional.of(new Market(result.getId(),
        result.getName(), result.getOwner()));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return dbMarket;
  }
}


