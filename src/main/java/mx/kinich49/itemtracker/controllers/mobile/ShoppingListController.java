package mx.kinich49.itemtracker.controllers.mobile;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.mobile.MobileShoppingList;
import mx.kinich49.itemtracker.models.mobile.responses.MobileShoppingListResponse;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import mx.kinich49.itemtracker.requests.mobile.MobileShoppingListRequest;
import mx.kinich49.itemtracker.services.MobileShoppingService;
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
    @Autowired
    private final MobileShoppingService shoppingListService;

    @GetMapping
    private ResponseEntity<JsonApi<List<MobileShoppingList>>> getAllShoppingLists() {
        return Optional.of(shoppingListRepository.findAll())
                .map(MobileShoppingList::from)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JsonApi<MobileShoppingList>> getShoppingListById(@PathVariable long id) {
        return shoppingListRepository.findById(id)
                .map(MobileShoppingList::from)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<JsonApi<MobileShoppingListResponse>>
    insertShopping(@RequestBody MobileShoppingListRequest shoppingList) {
        try {
            shoppingList.setUserId(1L);
            return Optional.of(shoppingListService.save(shoppingList))
                    .map(JsonApi::new)
                    .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShoppingList(@PathVariable long id) {
        shoppingListRepository.deleteById(id);
    }
}
