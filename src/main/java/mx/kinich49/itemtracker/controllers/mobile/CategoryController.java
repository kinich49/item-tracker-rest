package mx.kinich49.itemtracker.controllers.mobile;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.dtos.mobile.MobileCategory;
import mx.kinich49.itemtracker.models.Category;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("mobileCategoryController")
@RequestMapping("api/mobile/categories")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class CategoryController {

    @Autowired
    private final CategoryRepository categoryRepository;

    @GetMapping
    private ResponseEntity<JsonApi<List<MobileCategory>>> getAllCategories() {
        return Optional.of(categoryRepository.findAll())
                .map(MobileCategory::from)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JsonApi<MobileCategory>> getCategoryById(@PathVariable long id) {
        return categoryRepository.findById(id)
                .map(MobileCategory::from)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<JsonApi<Category>> insertCategory(@RequestBody Category category) {
        return Optional.of(categoryRepository.save(category))
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long id) {
        categoryRepository.deleteById(id);
    }
}
