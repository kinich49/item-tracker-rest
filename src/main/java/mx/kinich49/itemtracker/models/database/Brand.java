package mx.kinich49.itemtracker.models.database;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "brands")
@ToString(exclude = {"items"})
@EqualsAndHashCode(exclude = {"items"})
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(
            mappedBy = "brand",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
        item.setBrand(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setBrand(null);
    }
}