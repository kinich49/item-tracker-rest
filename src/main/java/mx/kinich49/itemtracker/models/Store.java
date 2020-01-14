package mx.kinich49.itemtracker.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "Stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @NotNull
    private String name;
    @OneToMany(
        mappedBy = "store",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<Shopping> shoppings;
}
