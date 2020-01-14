package mx.kinich49.itemtracker.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "ShoppingItems")
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
    @JoinColumn(name = "shopping_id")
    private Shopping shopping;
}
