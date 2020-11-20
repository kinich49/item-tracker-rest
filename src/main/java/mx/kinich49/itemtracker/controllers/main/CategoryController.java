package mx.kinich49.itemtracker.controllers.main;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.models.database.Category;
import mx.kinich49.itemtracker.models.front.FrontCategory;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("mainCategoryController")
@RequestMapping("api/categories")
@CrossOrigin
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class CategoryController {

    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<JsonApi<List<FrontCategory>>> getAllCategories() {
        return Optional.of(categoryService.findAll())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "id")
    public ResponseEntity<JsonApi<FrontCategory>> getCategoryById(@RequestParam long id) {
        return categoryService.findById(id)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "name")
    public ResponseEntity<JsonApi<List<FrontCategory>>> getCategoriesLike(@RequestParam String name) {
        return Optional.ofNullable(categoryService.findLike(name))
                .filter(list -> !list.isEmpty())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping
    public ResponseEntity<JsonApi<FrontCategory>> postCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long id) {
        categoryRepository.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<JsonApi<FrontCategory>> putCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
