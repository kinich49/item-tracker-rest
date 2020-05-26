package mx.kinich49.itemtracker.repositories.impl;

import mx.kinich49.itemtracker.repositories.ItemRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.*;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Optional<Tuple> findLatestStoreAndShoppingDateAndPrice(long itemId) {
        String query = "SELECT sl.store, sl.shoppingDate, si.unitPrice FROM ShoppingList sl JOIN sl.shoppingItems si " +
                "WHERE si.item.id =:itemId ORDER BY sl.shoppingDate DESC";
        List<Tuple> results = entityManager.createQuery(query, Tuple.class)
                .setParameter("itemId", itemId)
                .setMaxResults(1)
                .getResultList();
        if (results == null || results.isEmpty())
            return Optional.empty();
        return Optional.of(results.get(0));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Tuple>> findLatestStoreAndShoppingDateAndPriceForCategory(long categoryId) {
        String query = "SELECT sl.store, sl.shoppingDate, si.unitPrice, si.item.id " +
                "FROM ShoppingList sl JOIN sl.shoppingItems si " +
                "WHERE si.item.category.id =:categoryId ORDER BY sl.shoppingDate DESC";

        List<Tuple> result = entityManager.createQuery(query, Tuple.class)
                .setParameter("categoryId", categoryId)
                .getResultList();

        if (result == null || result.isEmpty())
            return Optional.empty();

        Map<Long, Tuple> map = new HashMap<>();
        result.forEach(tuple -> map.putIfAbsent(tuple.get(3, Long.class), tuple));
        return Optional.of(new ArrayList<>(map.values()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Tuple>> findLatestStoreAndShoppingDateAndPriceForBrand(long brandId) {
        String query = "SELECT sl.store, sl.shoppingDate, si.unitPrice, si.item.id " +
                "FROM ShoppingList sl JOIN sl.shoppingItems si " +
                "WHERE si.item.brand.id =:brandId ORDER BY sl.shoppingDate DESC";

        List<Tuple> result = entityManager.createQuery(query, Tuple.class)
                .setParameter("brandId", brandId)
                .getResultList();

        if (result == null || result.isEmpty())
            return Optional.empty();

        Map<Long, Tuple> map = new HashMap<>();
        result.forEach(tuple -> map.putIfAbsent(tuple.get(3, Long.class), tuple));
        return Optional.of(new ArrayList<>(map.values()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tuple> findAverageUnitPriceAndCurrency(long itemId) {
        String query = "SELECT avg(si.unitPrice), si.currency FROM ShoppingItem si" +
                " WHERE si.item.id =:itemId  GROUP BY si.currency";
        return entityManager.createQuery(query, Tuple.class)
                .setParameter("itemId", itemId)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Tuple>> findAverageUnitPriceAndCurrencyForCategory(long categoryId) {
        String query = "SELECT si.item, avg(si.unitPrice), si.currency FROM ShoppingItem si" +
                " WHERE si.item.category.id =:categoryId GROUP BY si.item.id, si.currency";
        List<Tuple> results = entityManager.createQuery(query, Tuple.class)
                .setParameter("categoryId", categoryId)
                .getResultList();

        return Optional.ofNullable(results);
    }

    @Override
    public Optional<List<Tuple>> findAverageUnitPriceAndCurrencyForBrand(long brandId) {
        String query = "SELECT si.item, avg(si.unitPrice), si.currency FROM ShoppingItem si" +
                " WHERE si.item.brand.id =:brandId GROUP BY si.item.id, si.currency";
        List<Tuple> results = entityManager.createQuery(query, Tuple.class)
                .setParameter("brandId", brandId)
                .getResultList();

        return Optional.ofNullable(results);
    }
}
