package mx.kinich49.itemtracker.services;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.database.Brand;
import mx.kinich49.itemtracker.models.database.Category;
import mx.kinich49.itemtracker.models.database.Store;
import mx.kinich49.itemtracker.models.database.User;
import mx.kinich49.itemtracker.repositories.BrandRepository;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.repositories.StoreRepository;
import mx.kinich49.itemtracker.repositories.UserRepository;
import mx.kinich49.itemtracker.requests.BaseShoppingListRequest;
import mx.kinich49.itemtracker.requests.main.BrandRequest;
import mx.kinich49.itemtracker.requests.main.CategoryRequest;
import mx.kinich49.itemtracker.requests.main.StoreRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

@RequiredArgsConstructor
public abstract class BaseDtoEntityService implements DtoEntityService {

    @Autowired
    protected final BrandRepository brandRepository;
    @Autowired
    protected final CategoryRepository categoryRepository;
    @Autowired
    protected final StoreRepository storeRepository;
    @Autowired
    protected final UserRepository userRepository;

    protected final Function<BrandRequest, Brand> brandSupplier = request -> {
        Brand brand = new Brand();
        brand.setName(request.getName());
        return brand;
    };

    protected final Function<CategoryRequest, Category> categorySupplier = request -> {
        Category category = new Category();
        category.setName(request.getName());
        return category;
    };

    protected final Function<StoreRequest, Store> storeSupplier = request -> {
        Store store = new Store();
        store.setName(request.getName());
        return store;
    };

    @Override
    public Brand from(BrandRequest request) {
        if (request == null || request.getId() == null)
            return brandSupplier.apply(request);

        return brandRepository.findById(request.getId())
                .orElse(brandSupplier.apply(request));
    }

    @Override
    public Category from(CategoryRequest request) {
        if (request == null || request.getId() == null)
            return categorySupplier.apply(request);

        return categoryRepository.findById(request.getId())
                .orElse(categorySupplier.apply(request));
    }

    @Override
    public Store from(StoreRequest request) {
        if (request == null || request.getId() == null)
            return storeSupplier.apply(request);

        return storeRepository.findById(request.getId())
                .orElse(storeSupplier.apply(request));
    }

    protected <T extends BaseShoppingListRequest> User requireExistingUser(T request) throws UserNotFoundException {
        return userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));
    }

}
