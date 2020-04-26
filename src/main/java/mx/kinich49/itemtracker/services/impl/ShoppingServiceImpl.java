package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.Constants;
import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.dtos.ShoppingDto;
import mx.kinich49.itemtracker.dtos.ShoppingItemDto;
import mx.kinich49.itemtracker.dtos.StoreDto;
import mx.kinich49.itemtracker.models.Category;
import mx.kinich49.itemtracker.models.Shopping;
import mx.kinich49.itemtracker.models.ShoppingItem;
import mx.kinich49.itemtracker.models.Store;
import mx.kinich49.itemtracker.repositories.ShoppingRepository;
import mx.kinich49.itemtracker.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final ShoppingRepository repository;

    @Autowired
    public ShoppingServiceImpl(ShoppingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ShoppingDto> loadById(long id) {
        return repository.findById(id)
                .map(this::from);
    }

    @Override
    public Optional<ShoppingDto> save(Shopping fromRequest) {
        return Optional.of(repository.save(fromRequest))
                .map(this::from);
    }

    private ShoppingDto from(Shopping shopping) {
        StoreDto storeDto = from(shopping.getStore());

        List<ShoppingItemDto> itemDtos = shopping.getShoppingItems()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return new ShoppingDto(shopping.getId(), shopping.getShoppingDate(), storeDto, itemDtos);
    }

    private ShoppingItemDto from(ShoppingItem item) {
        CategoryDto categoryDto = from(item.getItem().getCategory());

        DecimalFormat df = new DecimalFormat("#.###");
        String quantityDto;
        double unitPrice = item.getUnitPrice() / Constants.PRICE_SCALE;
        double totalPrice = unitPrice * item.getQuantity();

        //Only supporting KG for now
        if ("KG".equals(item.getUnit())) {
            quantityDto = df.format(item.getQuantity()) + " KG";
        } else {
            quantityDto = Integer.toString(((int) item.getQuantity()));
        }

        //only supporting MXN for now
        //Add conversion later
        df = new DecimalFormat("#.##");
        String unitPriceDto = "$" + df.format(unitPrice) + " MXN";
        String totalPriceDto = df.format(totalPrice) + " MXN";
        return new ShoppingItemDto(item.getId(), item.getItem().getName(), quantityDto,
                unitPriceDto, totalPriceDto, categoryDto);
    }

    private CategoryDto from(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    private StoreDto from(Store store) {
        return new StoreDto(store.getId(), store.getName());
    }

}