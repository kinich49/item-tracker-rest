package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.dtos.ShoppingDto;
import mx.kinich49.itemtracker.models.Shopping;
import mx.kinich49.itemtracker.repositories.ShoppingRepository;
import mx.kinich49.itemtracker.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shopping")
@SuppressWarnings("unused")
public class ShoppingController {

    private final ShoppingRepository repository;
    private final ShoppingService service;

    @Autowired
    public ShoppingController(ShoppingRepository repository,
                              ShoppingService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ShoppingDto> getShopping(@PathVariable("id") long shoppingId) {
        return service.loadById(shoppingId)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> insertShopping(@RequestBody Shopping category) {
        return new ResponseEntity<>(repository.save(category), HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShopping(@PathVariable("id") long shoppingListId) {
        repository.deleteById(shoppingListId);
    }
}
