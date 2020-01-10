package mx.kinich49.itemtracker.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ShoppingLists")
public class ShoppingList {

    @Id
    @GeneratedValue
    private long id;

    private LocalDate shoppingDate;
    @OneToMany(
            mappedBy = "shoppingList",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ShoppingItem> shoppingItems = new ArrayList<>();


}
