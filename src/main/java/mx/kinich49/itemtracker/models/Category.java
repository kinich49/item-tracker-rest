package mx.kinich49.itemtracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Categories")
@ToString(exclude = {"items"})
@EqualsAndHashCode(exclude = {"items"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
        item.setCategory(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setCategory(null);
    }
}