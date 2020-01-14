package mx.kinich49.itemtracker.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.kinich49.itemtracker.Constants;
import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.dtos.ShoppingDto;
import mx.kinich49.itemtracker.dtos.ShoppingItemDto;
import mx.kinich49.itemtracker.dtos.StoreDto;
import mx.kinich49.itemtracker.exceptions.ShoppingNotFoundException;
import mx.kinich49.itemtracker.models.Category;
import mx.kinich49.itemtracker.models.Shopping;
import mx.kinich49.itemtracker.models.ShoppingItem;
import mx.kinich49.itemtracker.models.Store;
import mx.kinich49.itemtracker.repositories.ShoppingRepository;

@Service
public class ShoppingService {

    private ShoppingRepository repository;

    @Autowired
    public ShoppingService(ShoppingRepository repository) {
        this.repository = repository;
    }

    public ShoppingDto load(long shoppingId) throws ShoppingNotFoundException {
        Shopping shopping = repository.findById(shoppingId)
            .orElseThrow(() -> new ShoppingNotFoundException(shoppingId));

        return from(shopping);
    }

    private ShoppingDto from(Shopping shopping) {
        StoreDto storeDto = from(shopping.getStore());

        List<ShoppingItemDto> itemDtos = shopping.getShoppingItems()
        .stream()
        .map(item -> from(item))
        .collect(Collectors.toList());

        return new ShoppingDto(shopping.getId(), itemDtos, storeDto);
    }

    private ShoppingItemDto from(ShoppingItem item) {
        CategoryDto categoryDto = from(item.getItem().getCategory());

        double quantity = item.getQuantity();
        double unitPrice = item.getUnitPrice() / Constants.PRICE_SCALE;
        double totalPrice = quantity * unitPrice;

        return new ShoppingItemDto(item.getId(), quantity, null, unitPrice, totalPrice, categoryDto);
    }

    private CategoryDto from(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    private StoreDto from(Store store) {
        return new StoreDto(store.getId(), store.getName());
    }
}