package mx.kinich49.itemtracker.repositories.impl;

import mx.kinich49.itemtracker.models.database.Item;
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
    public Optional<Tuple> findLatestStoreAndShoppingDateAndPrice(long itemId, long userId) {
        String query = "SELECT si.item, sl.store, sl.shoppingDate, si.unitPrice " +
                "FROM ShoppingList sl " +
                "JOIN sl.shoppingItems si " +
                "WHERE sl.user.id =:userId " +
                "AND si.item.id =:itemId " +
                "ORDER BY sl.shoppingDate DESC";
        List<Tuple> results = entityManager.createQuery(query, Tuple.class)
                .setParameter("userId", userId)
                .setParameter("itemId", itemId)
                .setMaxResults(1)
                .getResultList();
        if (results == null || results.isEmpty())
            return Optional.empty();
        return Optional.of(results.get(0));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tuple> findAverageUnitPriceAndCurrency(long itemId, long userId) {
        String query = "SELECT si.item, avg(si.unitPrice), si.currency " +
                "FROM ShoppingItem si " +
                "WHERE si.item.id =:itemId " +
                "GROUP BY si.currency";
        return entityManager.createQuery(query, Tuple.class)
                .setParameter("itemId", itemId)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tuple> findLatestStoreAndShoppingDateAndPriceForCategory(long categoryId, long userId) {
        String query = "SELECT si.item, sl.store, sl.shoppingDate, si.unitPrice " +
                "FROM ShoppingList sl JOIN sl.shoppingItems si " +
                "WHERE sl.user.id =:userId " +
                "AND si.item.category.id =:categoryId " +
                "ORDER BY sl.shoppingDate DESC";

        List<Tuple> result = entityManager.createQuery(query, Tuple.class)
                .setParameter("userId", userId)
                .setParameter("categoryId", categoryId)
                .getResultList();

        if (result == null || result.isEmpty())
            return null;

        Map<Long, Tuple> map = new HashMap<>();
        result.forEach(tuple -> {
            Item item = tuple.get(0, Item.class);
            map.putIfAbsent(item.getId(), tuple);
        });
        return new ArrayList<>(map.values());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tuple> findLatestStoreAndShoppingDateAndPriceForBrand(long brandId, long userId) {
        String query = "SELECT si.item, sl.store, sl.shoppingDate, si.unitPrice " +
                "FROM ShoppingList sl " +
                "JOIN sl.shoppingItems si " +
                "WHERE sl.user.id =:userId " +
                "AND si.item.brand.id =:brandId " +
                "ORDER BY sl.shoppingDate DESC";

        List<Tuple> result = entityManager.createQuery(query, Tuple.class)
                .setParameter("userId", userId)
                .setParameter("brandId", brandId)
                .getResultList();

        if (result == null || result.isEmpty())
            return null;

        Map<Long, Tuple> map = new HashMap<>();
        result.forEach(tuple -> {
            Item item = tuple.get(0, Item.class);
            map.putIfAbsent(item.getId(), tuple);
        });
        return new ArrayList<>(map.values());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tuple> findAverageUnitPriceAndCurrencyForCategory(long categoryId, long userId) {
        String query = "SELECT si.item, avg(si.unitPrice), si.currency " +
                "FROM ShoppingList sl " +
                "JOIN sl.shoppingItems si " +
                "WHERE sl.user.id =:userId " +
                "AND si.item.category.id =:categoryId " +
                "GROUP BY si.item.id, si.currency";

        return entityManager.createQuery(query, Tuple.class)
                .setParameter("userId", userId)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public List<Tuple> findAverageUnitPriceAndCurrencyForBrand(long brandId, long userId) {
        String query = "SELECT si.item, avg(si.unitPrice), si.currency " +
                "FROM ShoppingList sl " +
                "JOIN sl.shoppingItems si " +
                "WHERE sl.user.id =:userId " +
                "AND si.item.brand.id =:brandId " +
                "GROUP BY si.item.id, si.currency";
        return entityManager.createQuery(query, Tuple.class)
                .setParameter("userId", userId)
                .setParameter("brandId", brandId)
                .getResultList();
    }
}
