package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.dtos.StoreDto;
import mx.kinich49.itemtracker.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/stores")
@CrossOrigin
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<StoreDto>> getItemsLike(@RequestParam String name) {
        return storeService.findLike(name)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
