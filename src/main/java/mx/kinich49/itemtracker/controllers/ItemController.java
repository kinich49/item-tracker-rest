package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.dtos.ItemDto;
import mx.kinich49.itemtracker.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<ItemDto>> getItemsLike(@RequestParam String name) {
        return itemService.findLike(name)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
