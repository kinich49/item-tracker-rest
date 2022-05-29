package mx.kinich49.itemtracker.controllers.main;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.front.FrontShoppingList;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.services.ShoppingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingController.class);
    @Autowired
    private final ShoppingService shoppingListService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonApi<FrontShoppingList>> getShopping(@PathVariable("id") long shoppingId,
                                                                  @AuthenticationPrincipal UserDetails userDetails) {

        try {
            return shoppingListService.findBy(shoppingId, userDetails)
                    .map(JsonApi::new)
                    .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (BusinessException e) {
            return new ResponseEntity<>(new JsonApi<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(params = "shoppingDate")
    public ResponseEntity<JsonApi<List<FrontShoppingList>>> getShoppingLists(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate shoppingDate,
                                                                             @AuthenticationPrincipal UserDetails userDetails) {
        return Optional.ofNullable(shoppingListService.findBy(shoppingDate, userDetails))
                .filter(dtos -> !dtos.isEmpty())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<JsonApi<FrontShoppingList>> insertShopping(@RequestBody MainShoppingListRequest shoppingList,
                                                                     @AuthenticationPrincipal UserDetails userDetails) {
        try {
            return shoppingListService.save(shoppingList, userDetails)
                    .map(JsonApi::new)
                    .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                    .orElseThrow(() -> new BusinessException("Something went wrong"));
        } catch (BusinessException e) {
            return new ResponseEntity<>(new JsonApi<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShopping(@PathVariable("id") long shoppingListId,
                               @AuthenticationPrincipal UserDetails userDetails) {
        try {
            shoppingListService.deleteBy(shoppingListId, userDetails);
        } catch (BusinessException e) {
            LOGGER.info("User {} tried to delete shopping list with id {} but failed to verify ownership",
                    userDetails.getUsername(), shoppingListId);
        }

    }
}
