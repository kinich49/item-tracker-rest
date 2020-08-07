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

import java.util.Optional;

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

    @Override
    public Brand from(BrandRequest request) {
        if (request == null)
            return null;

        return Optional.ofNullable(request.getId())
                .flatMap(brandRepository::findById)
                .orElseGet(() -> {
                    Brand brand = new Brand();
                    brand.setName(request.getName());
                    return brand;
                });
    }

    @Override
    public Category from(CategoryRequest request) {
        if (request == null)
            return null;

        return Optional.ofNullable(request.getId())
                .flatMap(categoryRepository::findById)
                .orElseGet(() -> {
                    Category category = new Category();
                    category.setName(request.getName());
                    return category;
                });
    }

    @Override
    public Store from(StoreRequest request) {
        if (request == null)
            return null;

        return Optional.ofNullable(request.getId())
                .flatMap(storeRepository::findById)
                .orElseGet(() -> {
                    Store store = new Store();
                    store.setName(request.getName());
                    return store;
                });
    }

    protected <T extends BaseShoppingListRequest> User requireExistingUser(T request) throws UserNotFoundException {
        return userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));
    }

}
