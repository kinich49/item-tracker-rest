package mx.kinich49.itemtracker.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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
    @NotNull
    private int unitPrice;
    @NotNull
    private int totalPrice;
    @OneToOne
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShoppingList shoppingList;
}
