package mx.kinich49.itemtracker.controllers.mobile;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.dtos.mobile.MobileItem;
import mx.kinich49.itemtracker.models.Item;
import mx.kinich49.itemtracker.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("mobileItemController")
@RequestMapping("api/mobile/items")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ItemController {

    @Autowired
    private final ItemRepository itemRepository;

    @GetMapping
    private ResponseEntity<JsonApi<List<MobileItem>>> getAllItems() {
        return Optional.of(itemRepository.findAll())
                .map(MobileItem::from)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JsonApi<MobileItem>> getItemById(@PathVariable long id) {
        return itemRepository.findById(id)
                .map(MobileItem::from)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<JsonApi<Item>> insertItem(@RequestBody Item item) {
        return Optional.of(itemRepository.save(item))
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable long id) {
        itemRepository.deleteById(id);
    }
}
