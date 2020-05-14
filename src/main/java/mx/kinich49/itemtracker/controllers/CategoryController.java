package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.models.Category;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
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
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Category> getCategoryById(@RequestParam long id) {
        return categoryRepository.findById(id)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<CategoryDto>> getCategoriesLike(@RequestParam String name) {
        return categoryService.findLike(name)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> postCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category)
                .map(persistedCategory -> new ResponseEntity<>(persistedCategory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long id) {
        categoryRepository.deleteById(id);
    }
}
