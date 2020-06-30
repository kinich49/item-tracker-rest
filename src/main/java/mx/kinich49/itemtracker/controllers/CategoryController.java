package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.models.Category;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categories")
@CrossOrigin
@SuppressWarnings("unused")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository,
                              CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<JsonApi<List<Category>>> getAllCategories() {
        return Optional.of(categoryRepository.findAll())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "id")
    public ResponseEntity<JsonApi<CategoryDto>> getCategoryById(@RequestParam long id) {
        return categoryService.findById(id)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "name")
    public ResponseEntity<JsonApi<List<CategoryDto>>> getCategoriesLike(@RequestParam String name) {
        return Optional.ofNullable(categoryService.findLike(name))
                .filter(list -> !list.isEmpty())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping
    public ResponseEntity<JsonApi<CategoryDto>> postCategory(@RequestBody Category category) {
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
    public ResponseEntity<JsonApi<CategoryDto>> putCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
