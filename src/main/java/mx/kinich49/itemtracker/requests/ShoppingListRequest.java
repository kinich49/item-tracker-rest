package mx.kinich49.itemtracker.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ShoppingListRequest {

    private long userId;
    private Store store;
    private LocalDate shoppingDate;
    private List<ShoppingItem> shoppingItems = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class ShoppingItem {
        private long itemId;
        private String unit;
        private int quantity;
        private int unitPrice;
        private String currency;
        private String name;
        private Brand brand;
        private Category category;
    }

    @Data
    @NoArgsConstructor
    public static class Brand {
        private long id;
        private String name;
    }

    @Data
    @NoArgsConstructor
    public static class Category {
        private long id;
        private String name;
    }

    @Data
    @NoArgsConstructor
    public static class Store {
        private long id;
        private String name;
    }
}
