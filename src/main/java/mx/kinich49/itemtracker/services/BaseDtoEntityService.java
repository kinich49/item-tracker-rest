package mx.kinich49.itemtracker.services;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.models.database.*;
import mx.kinich49.itemtracker.repositories.BrandRepository;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.repositories.StoreRepository;
import mx.kinich49.itemtracker.repositories.UserRepository;
import mx.kinich49.itemtracker.requests.BaseShoppingListRequest;
import mx.kinich49.itemtracker.requests.main.BrandRequest;
import mx.kinich49.itemtracker.requests.main.CategoryRequest;
import mx.kinich49.itemtracker.requests.main.StoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

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

        //Search by id if it's not null
        //Or if it's null or it wasn't found by id
        //search by name instead
        //Create new instance if it wasn't found
        return Optional.ofNullable(request.getId())
                .flatMap(brandRepository::findById)
                .orElseGet(() -> brandRepository.findByName(request.getName())
                        .orElseGet(() -> {
                            Brand brand = new Brand();
                            brand.setName(request.getName());
                            return brandRepository.save(brand);
                        }));
    }

    @Override
    public Category from(CategoryRequest request) {
        if (request == null)
            return null;

        //Search by id if it's not null
        //Or if it's null or it wasn't found by id
        //search by name instead
        //Create new instance if it wasn't found
        return Optional.ofNullable(request.getId())
                .flatMap(categoryRepository::findById)
                .orElseGet(() -> categoryRepository.findByName(request.getName())
                        .orElseGet(() -> {
                            Category category = new Category();
                            category.setName(request.getName());
                            return categoryRepository.save(category);
                        }));
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
                    return storeRepository.save(store);
                });
    }

}
