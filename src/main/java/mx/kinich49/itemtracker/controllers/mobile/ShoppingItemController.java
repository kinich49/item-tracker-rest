package mx.kinich49.itemtracker.controllers.mobile;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.models.ShoppingItem;
import mx.kinich49.itemtracker.repositories.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("mobileShoppingItemController")
@RequestMapping("api/mobile/shoppingItems")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ShoppingItemController {

    @Autowired
    private final ShoppingItemRepository shoppingItemRepository;

    @GetMapping
    private ResponseEntity<JsonApi<List<ShoppingItem>>> getAllShoppingItems() {
        return Optional.of(shoppingItemRepository.findAll())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JsonApi<ShoppingItem>> getShoppingItemById(@PathVariable long id) {
        return shoppingItemRepository.findById(id)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<JsonApi<ShoppingItem>> insertShoppingItem(@RequestBody ShoppingItem shoppingItem) {
        return Optional.of(shoppingItemRepository.save(shoppingItem))
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShoppingItem(@PathVariable long id) {
        shoppingItemRepository.deleteById(id);
    }
}
