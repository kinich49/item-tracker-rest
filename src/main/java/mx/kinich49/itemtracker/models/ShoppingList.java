package mx.kinich49.itemtracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ShoppingLists")
@ToString(exclude = {"shoppingItems"})
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate shoppingDate = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    @OneToMany(
            mappedBy = "shoppingList",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<ShoppingItem> shoppingItems = new ArrayList<>();

    public void addShoppingItem(ShoppingItem shoppingItem) {
        shoppingItems.add(shoppingItem);
        shoppingItem.setShoppingList(this);
    }

    public void removeShoppingItem(ShoppingItem shoppingItem) {
        shoppingItems.remove(shoppingItem);
        shoppingItem.setShoppingList(null);
    }

}
