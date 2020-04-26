package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.models.Brand;
import mx.kinich49.itemtracker.repositories.BrandRepository;
import mx.kinich49.itemtracker.sevices.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/brands")
@SuppressWarnings("unused")
public class BrandController {

    private final BrandRepository brandRepository;
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandRepository brandRepository,
                           BrandService brandService) {
        this.brandRepository = brandRepository;
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        return new ResponseEntity<>(brandRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Brand> getBrandById(@RequestParam long id) {
        return brandRepository.findById(id)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Brand> postBrand(@RequestBody Brand request) {
        return brandService.saveBrand(request)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable long id) {
        brandRepository.deleteById(id);
    }
}
