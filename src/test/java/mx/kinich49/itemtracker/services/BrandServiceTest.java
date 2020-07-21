package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.models.front.FrontBrand;
import mx.kinich49.itemtracker.models.database.Brand;
import mx.kinich49.itemtracker.repositories.BrandRepository;
import mx.kinich49.itemtracker.services.impl.BrandServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @InjectMocks
    private BrandServiceImpl subject;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private SuggestionService suggestionService;

    @Test
    @DisplayName("Result should be updated brand")
    public void shouldUpdate_existingBrand_andReturnDto() {
        //given
        Brand fromRequest = new Brand();
        fromRequest.setName("Updated Brand Name");
        fromRequest.setId(15L);

        Brand fromPersistence = new Brand();
        fromPersistence.setId(15L);
        fromPersistence.setName("Original Brand Name");
        when(brandRepository.findById(eq(15L)))
                .thenReturn(Optional.of(fromPersistence));

        when(brandRepository.save(any(Brand.class)))
                .thenReturn(fromPersistence);
        //when
        Optional<FrontBrand> result = subject.updateBrand(fromRequest);


        //then
        assertTrue(result.isPresent());
        assertEquals("Updated Brand Name", result.get().getName());
        assertEquals(15L, result.get().getId());
    }

    @Test
    @DisplayName("Result should be empty when brand is not valid")
    public void shouldReturn_emptyOptional_whenBrandDoesNotExists() {
        //given
        Brand fromRequest = new Brand();
        fromRequest.setId(Long.MAX_VALUE);
        fromRequest.setName("Updated Brand Name");

        when(brandRepository.findById(eq(Long.MAX_VALUE)))
                .thenReturn(Optional.empty());

        //when
        Optional<FrontBrand> result = subject.updateBrand(fromRequest);

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Result should be empty when request is null")
    public void shouldReturn_emptyOptional_whenRequestIsNotValid() {
        //when
        Optional<FrontBrand> result = subject.updateBrand(null);

        //then
        assertFalse(result.isPresent());
    }

}
