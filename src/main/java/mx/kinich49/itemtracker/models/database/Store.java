package mx.kinich49.itemtracker.models.database;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Stores")
@ToString(exclude = {"shoppingLists"})
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ShoppingList> shoppingLists = new ArrayList<>();

    public void addShoppingList(ShoppingList shoppingList) {
        shoppingLists.add(shoppingList);
        shoppingList.setStore(this);
    }

    public void removeShoppingList(ShoppingList shoppingList) {
        shoppingLists.remove(shoppingList);
        shoppingList.setStore(null);
    }
}
