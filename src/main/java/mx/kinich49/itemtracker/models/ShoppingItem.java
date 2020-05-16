package mx.kinich49.itemtracker.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "ShoppingItems")
@ToString(exclude = {"shoppingList"})
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity = 1;
    private String unit;
    @NotNull
    private double unitPrice;
    @NotNull
    private String currency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingList_id")
    private ShoppingList shoppingList;
}
