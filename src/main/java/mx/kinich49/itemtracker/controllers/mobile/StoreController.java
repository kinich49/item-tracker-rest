package mx.kinich49.itemtracker.controllers.mobile;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.dtos.mobile.MobileStore;
import mx.kinich49.itemtracker.models.Store;
import mx.kinich49.itemtracker.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("mobileStoreController")
@RequestMapping("api/mobile/stores")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class StoreController {

    @Autowired
    private final StoreRepository storeRepository;

    @GetMapping
    private ResponseEntity<JsonApi<List<MobileStore>>> getAllStores() {
        return Optional.of(storeRepository.findAll())
                .map(MobileStore::from)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JsonApi<MobileStore>> getStoreById(@PathVariable long id) {
        return storeRepository.findById(id)
                .map(MobileStore::from)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<JsonApi<Store>> insertStore(@RequestBody Store store) {
        return Optional.of(storeRepository.save(store))
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStore(@PathVariable long id) {
        storeRepository.deleteById(id);
    }

}
