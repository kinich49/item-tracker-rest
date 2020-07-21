package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.models.front.FrontCategory;
import mx.kinich49.itemtracker.models.database.Category;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.services.impl.CategoryServiceImpl;
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
public class CategoryServiceTest {
    @InjectMocks
    private CategoryServiceImpl subject;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private SuggestionService suggestionService;

    @Test
    @DisplayName("Result should be updated category")
    public void shouldUpdate_existingBrand_andReturnDto() {
        //given
        Category fromRequest = new Category();
        fromRequest.setName("Updated Category Name");
        fromRequest.setId(15L);

        Category fromPersistence = new Category();
        fromPersistence.setId(15L);
        fromPersistence.setName("Original Category Name");
        when(categoryRepository.findById(eq(15L)))
                .thenReturn(Optional.of(fromPersistence));

        when(categoryRepository.save(any(Category.class)))
                .thenReturn(fromPersistence);
        //when
        Optional<FrontCategory> result = subject.updateCategory(fromRequest);


        //then
        assertTrue(result.isPresent());
        assertEquals("Updated Category Name", result.get().getName());
        assertEquals(15L, result.get().getId());
    }

    @Test
    @DisplayName("Result should be empty when category is not valid")
    public void shouldReturn_emptyOptional_whenBrandDoesNotExists() {
        //given
        Category fromRequest = new Category();
        fromRequest.setId(Long.MAX_VALUE);
        fromRequest.setName("Updated Category Name");

        when(categoryRepository.findById(eq(Long.MAX_VALUE)))
                .thenReturn(Optional.empty());

        //when
        Optional<FrontCategory> result = subject.updateCategory(fromRequest);

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Result should be empty when request is null")
    public void shouldReturn_emptyOptional_whenRequestIsNotValid() {
        //when
        Optional<FrontCategory> result = subject.updateCategory(null);

        //then
        assertFalse(result.isPresent());

    }
}
