package mx.kinich49.itemtracker.repositories;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface ItemRepositoryCustom {

    Optional<Tuple> findLatestStoreAndShoppingDateAndPrice(long itemId);

    List<Tuple> findAverageUnitPriceAndCurrency(long itemId);
}