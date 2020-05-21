package mx.kinich49.itemtracker.repositories.impl;

import mx.kinich49.itemtracker.repositories.ItemRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Optional<Tuple> findLatestStoreAndShoppingDateAndPrice(long itemId) {
        String query = "SELECT sl.store, MAX(sl.shoppingDate), si.unitPrice FROM ShoppingList sl JOIN sl.shoppingItems si " +
                "WHERE si.item.id =:itemId GROUP BY si.item.id, sl.store.id ORDER BY sl.shoppingDate DESC";
        Tuple result = entityManager.createQuery(query, Tuple.class)
                .setParameter("itemId", itemId)
                .setMaxResults(1)
                .getSingleResult();
        return Optional.ofNullable(result);
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

}
