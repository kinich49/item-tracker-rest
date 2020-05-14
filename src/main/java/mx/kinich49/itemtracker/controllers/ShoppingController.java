package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.dtos.ShoppingListDto;
import mx.kinich49.itemtracker.models.ShoppingList;
import mx.kinich49.itemtracker.repositories.ShoppingRepository;
import mx.kinich49.itemtracker.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/shoppingLists")
@SuppressWarnings("unused")
@CrossOrigin
public class ShoppingController {

    private final ShoppingRepository repository;
    private final ShoppingService service;

    @Autowired
    public ShoppingController(ShoppingRepository repository,
                              ShoppingService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListDto> getShopping(@PathVariable("id") long shoppingId) {
        return service.loadById(shoppingId)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "shoppingDate")
    public ResponseEntity<List<ShoppingListDto>> getShoppingLists(@RequestParam
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                          LocalDate shoppingDate) {
        return Optional.ofNullable(service.loadByDate(shoppingDate))
                .filter(dtos -> !dtos.isEmpty())
                .map(dtos -> new ResponseEntity<>(dtos, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ShoppingListDto> insertShopping(@RequestBody ShoppingList shoppingList) {
        return service.save(shoppingList)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShopping(@PathVariable("id") long shoppingListId) {
        repository.deleteById(shoppingListId);
    }
}
