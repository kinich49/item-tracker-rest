package mx.kinich49.itemtracker.controllers.main;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.dtos.ItemAnalyticsDto;
import mx.kinich49.itemtracker.models.front.FrontItem;
import mx.kinich49.itemtracker.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController("mainItemController")
@RequestMapping("api/items")
@CrossOrigin
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ItemController {

    @Autowired
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<JsonApi<List<FrontItem>>> findAll() {
        return Optional.ofNullable(itemService.findAll())
                .filter(list -> !list.isEmpty())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(params = "name")
    public ResponseEntity<JsonApi<List<FrontItem>>> getItemsLike(@RequestParam String name) {
        return Optional.ofNullable(itemService.findLike(name))
                .filter(list -> !list.isEmpty())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(path = "/{id}/analytics")
    public ResponseEntity<JsonApi<List<ItemAnalyticsDto>>> getAnalyticsForItem(@PathVariable(value = "id") long itemId) {
        return itemService.getAnalyticsFor(itemId, 1L)
                .map(analytics -> {
                    List<ItemAnalyticsDto> list = new ArrayList<>();
                    list.add(analytics);
                    return list;
                })
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/category/{id}/analytics")
    public ResponseEntity<JsonApi<List<ItemAnalyticsDto>>> getAnalyticsForCategory(
            @PathVariable(value = "id") long categoryId) {
        return Optional.ofNullable(itemService.getAnalyticsForCategory(categoryId, 1L))
                .filter(list -> !list.isEmpty())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/brand/{id}/analytics")
    public ResponseEntity<JsonApi<List<ItemAnalyticsDto>>> getAnalyticsForBrand(
            @PathVariable(value = "id") long brandId) {
        return Optional.ofNullable(itemService.getAnalyticsForBrand(brandId, 1L))
                .filter(list -> !list.isEmpty())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
