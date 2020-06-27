package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.dtos.ShoppingListDto;
import mx.kinich49.itemtracker.requests.ShoppingListRequest;
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

    private final ShoppingService shoppingListService;

    @Autowired
    public ShoppingController(ShoppingService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListDto> getShopping(@PathVariable("id") long shoppingId) {
        return shoppingListService.findBy(shoppingId, 1L)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "shoppingDate")
    public ResponseEntity<List<ShoppingListDto>> getShoppingLists(@RequestParam
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                          LocalDate shoppingDate) {
        return Optional.ofNullable(shoppingListService.findBy(shoppingDate, 1L))
                .filter(dtos -> !dtos.isEmpty())
                .map(dtos -> new ResponseEntity<>(dtos, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ShoppingListDto> insertShopping(@RequestBody ShoppingListRequest shoppingList) {
        return shoppingListService.save(shoppingList)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShopping(@PathVariable("id") long shoppingListId) {
        shoppingListService.deleteBy(shoppingListId);
    }
}
