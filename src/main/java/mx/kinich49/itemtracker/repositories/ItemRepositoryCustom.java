package mx.kinich49.itemtracker.repositories;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface ItemRepositoryCustom {

    Optional<Tuple> findLatestStoreAndShoppingDateAndPrice(long itemId);

    Optional<List<Tuple>> findLatestStoreAndShoppingDateAndPriceForCategory(long categoryId);

    Optional<List<Tuple>> findLatestStoreAndShoppingDateAndPriceForBrand(long categoryId);

    List<Tuple> findAverageUnitPriceAndCurrency(long itemId);

    Optional<List<Tuple>> findAverageUnitPriceAndCurrencyForCategory(long categoryId);

    Optional<List<Tuple>> findAverageUnitPriceAndCurrencyForBrand(long brandId);
}