package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.dtos.ItemAnalyticsDto;
import mx.kinich49.itemtracker.dtos.ItemDto;
import mx.kinich49.itemtracker.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/items")
@CrossOrigin
@SuppressWarnings("unused")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<ItemDto>> getItemsLike(@RequestParam String name) {
        return Optional.ofNullable(itemService.findLike(name))
                .filter(list -> !list.isEmpty())
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(path = "/{id}/analytics")
    public ResponseEntity<List<ItemAnalyticsDto>> getAnalyticsForItem(@PathVariable(value = "id") long itemId) {
        return itemService.getAnalyticsFor(itemId)
                .map(analytics -> {
                    List<ItemAnalyticsDto> list = new ArrayList<>();
                    list.add(analytics);
                    return list;
                })
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/category/{id}/analytics")
    public ResponseEntity<List<ItemAnalyticsDto>> getAnalyticsForCategory(
            @PathVariable(value = "id") long categoryId) {
        return Optional.ofNullable(itemService.getAnalyticsForCategory(categoryId))
                .filter(list -> !list.isEmpty())
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/brand/{id}/analytics")
    public ResponseEntity<List<ItemAnalyticsDto>> getAnalyticsForBrand(
            @PathVariable(value = "id") long brandId) {
        return Optional.ofNullable(itemService.getAnalyticsForBrand(brandId))
                .filter(list -> !list.isEmpty())
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
