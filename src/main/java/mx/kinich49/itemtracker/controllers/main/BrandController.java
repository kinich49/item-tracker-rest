package mx.kinich49.itemtracker.controllers.main;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.models.database.Brand;
import mx.kinich49.itemtracker.models.front.FrontBrand;
import mx.kinich49.itemtracker.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("mainBrandController")
@RequestMapping("api/brands")
@CrossOrigin
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class BrandController {

    @Autowired
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<JsonApi<List<FrontBrand>>> getAllBrands() {
        return Optional.of(brandService.findAll())
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "id")
    public ResponseEntity<JsonApi<FrontBrand>> getBrandById(@RequestParam long id) {
        return brandService.findById(id)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<JsonApi<FrontBrand>> postBrand(@RequestBody Brand request) {
        return brandService.saveBrand(request)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(params = "name")
    public ResponseEntity<JsonApi<List<FrontBrand>>> getBrandsLikeName(@RequestParam String name) {
        return Optional.ofNullable(brandService.findLike(name))
                .filter(list -> !list.isEmpty())
                .map(JsonApi::new)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable long id) {
        brandService.delete(id);
    }

    @PutMapping
    public ResponseEntity<JsonApi<FrontBrand>> updateBrand(@RequestBody Brand brand) {
        return brandService.updateBrand(brand)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
