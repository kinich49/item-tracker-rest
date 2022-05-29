package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.database.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    List<ShoppingList> findByShoppingDateAndUserId(LocalDate shoppingDate, Long userId);
    @Query("FROM ShoppingList sl " +
            "WHERE sl.shoppingDate = ?1  " +
            "AND sl.user.username = ?2")
    List<ShoppingList> findByShoppingDateAndUsername(LocalDate shoppingDate, String username);

    List<ShoppingList> findByUserId(Long userId);

    Optional<ShoppingList> findByIdAndUserId(Long shoppingId, Long userId);

}