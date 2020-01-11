package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.models.ShoppingList;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/shopping")
public class ShoppingListController {

    private ShoppingListRepository repository;

    @Autowired
    public ShoppingListController(ShoppingListRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getShoppingList(@PathVariable("id") long shoppingListId) {
        Optional<ShoppingList> optional = repository.findById(shoppingListId);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> insertShoppingList(@RequestBody ShoppingList category) {
        return new ResponseEntity<ShoppingList>(repository.save(category), HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShoppingList(@PathVariable("id") long shoppingListId) {
        repository.deleteById(shoppingListId);
    }
}
