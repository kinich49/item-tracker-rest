package mx.kinich49.itemtracker.models.database;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Items")
@ToString(exclude = {"items"})
@EqualsAndHashCode(exclude = {"items"})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @OneToMany(
            mappedBy = "item",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ShoppingItem> items = new ArrayList<>();

    public void addShoppingItem(ShoppingItem shoppingItem) {
        items.add(shoppingItem);
        shoppingItem.setItem(this);
    }

    public void removeShoppingItem(ShoppingItem shoppingItem) {
        items.remove(shoppingItem);
        shoppingItem.setItem(null);
    }
}
