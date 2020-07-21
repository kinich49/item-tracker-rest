package mx.kinich49.itemtracker.controllers.mobile;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.dtos.mobile.MobileBrand;
import mx.kinich49.itemtracker.models.Brand;
import mx.kinich49.itemtracker.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("mobileBrandController")
@RequestMapping("api/mobile/brands")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class BrandController {

    @Autowired
    private final BrandRepository brandRepository;

    @GetMapping
    private ResponseEntity<JsonApi<List<MobileBrand>>> getAllBrands() {
        return Optional.of(brandRepository.findAll())
                .map(MobileBrand::from)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JsonApi<MobileBrand>> getBrandById(@PathVariable long id) {
        return brandRepository.findById(id)
                .map(MobileBrand::from)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<JsonApi<Brand>> insertBrand(@RequestBody Brand brand) {
        return Optional.of(brandRepository.save(brand))
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable long id) {
        brandRepository.deleteById(id);
    }
}
