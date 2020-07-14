package mx.kinich49.itemtracker.controllers.mobile;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.models.ShoppingList;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("mobileShoppingListController")
@RequestMapping("api/mobile/shoppingLists")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ShoppingListController {

    @Autowired
    private final ShoppingListRepository shoppingListRepository;

    @GetMapping
    private ResponseEntity<JsonApi<List<ShoppingList>>> getAllShoppingLists() {
        return Optional.of(shoppingListRepository.findAll())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JsonApi<ShoppingList>> getShoppingListById(@PathVariable long id) {
        return shoppingListRepository.findById(id)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<JsonApi<ShoppingList>> insertShoppingList(@RequestBody ShoppingList shoppingList) {
        return Optional.of(shoppingListRepository.save(shoppingList))
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShoppingList(@PathVariable long id) {
        shoppingListRepository.deleteById(id);
    }
}
