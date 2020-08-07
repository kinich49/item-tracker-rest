package mx.kinich49.itemtracker.models.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ShoppingLists")
@ToString(exclude = {"shoppingItems", "user"})
public class ShoppingList {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate shoppingDate = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    @OneToMany(
            mappedBy = "shoppingList",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ShoppingItem> shoppingItems = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    public void addShoppingItem(ShoppingItem shoppingItem) {
        shoppingItems.add(shoppingItem);
        shoppingItem.setShoppingList(this);
    }

    public void removeShoppingItem(ShoppingItem shoppingItem) {
        shoppingItems.remove(shoppingItem);
        shoppingItem.setShoppingList(null);
    }

}