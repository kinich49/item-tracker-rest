package mx.kinich49.itemtracker.repositories;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface ItemRepositoryCustom {

    /**
     * @param itemId the item id
     * @param userId the user id
     * @return a list of tuples composed by
     * {@code Item} in index 0 - the item
     * {@code Store} in index 1 - the item's latest store
     * {@code LocalDate} in index 2 - the item's latest shopping date
     * {@code Integer} in index 3 - the item's latest unit price
     */
    Optional<Tuple> findLatestStoreAndShoppingDateAndPrice(long itemId, long userId);

    /**
     * @param categoryId the category id
     * @param userId     the user id
     * @return a list of tuples composed by
     * {@code Item} in index 0 - the item
     * {@code Store} in index 1 - the item's latest store
     * {@code LocalDate} in index 2 - the item's latest shopping date
     * {@code Integer} in index 3 - the item's latest unit price
     */
    List<Tuple> findLatestStoreAndShoppingDateAndPriceForCategory(long categoryId, long userId);

    /**
     * @param brandId the brand id
     * @param userId  the user id
     * @return a list of tuples composed by
     * {@code Item} in index 0 - the item
     * {@code Store} in index 1 - the item's latest store
     * {@code LocalDate} in index 2 - the item's latest shopping date
     * {@code Integer} in index 3 - the item's latest unit price
     */
    List<Tuple> findLatestStoreAndShoppingDateAndPriceForBrand(long brandId, long userId);

    /**
     * @param itemId the item id
     * @param userId the user id
     * @return a list of tuples composed by
     * {@code Item} in index 0 - the item
     * {@code Double} in index 1 - the average price for item in the same currency
     * {@code String} in index 2 - the currency for the item
     */
    List<Tuple> findAverageUnitPriceAndCurrency(long itemId, long userId);

    /**
     * @param categoryId the category id
     * @param userId     the user id
     * @return a list of tuples composed by
     * {@code Item} in index 0 - the item
     * {@code Double} in index 1 - the average price for item in the same currency
     * {@code String} in index 2 - the currency for the item
     */
    List<Tuple> findAverageUnitPriceAndCurrencyForCategory(long categoryId, long userId);

    /**
     * @param brandId the brand id
     * @param userId  the user id
     * @return a list of tuples composed by
     * {@code Item} in index 0 - the item
     * {@code Integer} in index 1 - the average price for item in the same currency
     * {@code String} in index 2 - the currency for the item
     */
    List<Tuple> findAverageUnitPriceAndCurrencyForBrand(long brandId, long userId);
}