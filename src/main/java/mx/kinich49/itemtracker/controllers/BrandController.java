package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.dtos.BrandDto;
import mx.kinich49.itemtracker.models.Brand;
import mx.kinich49.itemtracker.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/brands")
@CrossOrigin
@SuppressWarnings("unused")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @GetMapping(params = "id")
    public ResponseEntity<BrandDto> getBrandById(@RequestParam long id) {
        return brandService.findById(id)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BrandDto> postBrand(@RequestBody Brand request) {
        return brandService.saveBrand(request)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<BrandDto>> getBrandsLikeName(@RequestParam String name) {
        return Optional.ofNullable(brandService.findLike(name))
                .filter(list -> !list.isEmpty())
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable long id) {
        brandService.delete(id);
    }

    @PutMapping
    public ResponseEntity<BrandDto> updateBrand(@RequestBody Brand brand) {
        return brandService.updateBrand(brand)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
