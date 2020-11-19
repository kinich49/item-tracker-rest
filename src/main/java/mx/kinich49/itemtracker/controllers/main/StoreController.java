package mx.kinich49.itemtracker.controllers.main;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.models.front.FrontStore;
import mx.kinich49.itemtracker.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("mainStoreController")
@RequestMapping("api/stores")
@CrossOrigin
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class StoreController {

    @Autowired
    private final StoreService storeService;

    @GetMapping(params = "name")
    public ResponseEntity<JsonApi<List<FrontStore>>> getStoresLike(@RequestParam String name) {
        return Optional.ofNullable(storeService.findLike(name))
                .filter(list -> !list.isEmpty())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping
    public ResponseEntity<JsonApi<List<FrontStore>>> getStores() {
        return Optional.ofNullable(storeService.findAll())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
