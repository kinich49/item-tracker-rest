package mx.kinich49.itemtracker.controllers.main;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.front.FrontShoppingList;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController("mainShoppingListController")
@RequestMapping("api/shoppingLists")
@CrossOrigin
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ShoppingController {

    @Autowired
    private final ShoppingService shoppingListService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonApi<FrontShoppingList>> getShopping(@PathVariable("id") long shoppingId) {
        return shoppingListService.findBy(shoppingId, 1L)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "shoppingDate")
    public ResponseEntity<JsonApi<List<FrontShoppingList>>> getShoppingLists(@RequestParam
                                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                   LocalDate shoppingDate) {
        return Optional.ofNullable(shoppingListService.findBy(shoppingDate, 1L))
                .filter(dtos -> !dtos.isEmpty())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<?> insertShopping(@RequestBody MainShoppingListRequest shoppingList) {
        shoppingList.setUserId(1L);
        try {
            return shoppingListService.save(shoppingList)
                    .map(JsonApi::new)
                    .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                    .orElseThrow(() -> new BusinessException("Something went wrong"));
        } catch (BusinessException e) {
            return new ResponseEntity<>(new JsonApi<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShopping(@PathVariable("id") long shoppingListId) {
        shoppingListService.deleteBy(shoppingListId);
    }
}
