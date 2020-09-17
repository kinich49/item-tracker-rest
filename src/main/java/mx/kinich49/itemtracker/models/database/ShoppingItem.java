package mx.kinich49.itemtracker.models.database;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "ShoppingItems")
@ToString(exclude = {"shoppingList"})
@EqualsAndHashCode(exclude = {"shoppingList"})
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double quantity = 1;
    private String unit;
    @NotNull
    private int unitPrice;
    @NotNull
    private String currency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingList_id")
    private ShoppingList shoppingList;
}
