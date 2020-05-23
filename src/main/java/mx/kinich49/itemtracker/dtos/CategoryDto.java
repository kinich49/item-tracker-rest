package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import mx.kinich49.itemtracker.models.Category;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CategoryDto {

    private final long id;
    private final String name;

    public static CategoryDto from(Category category) {
        if (category == null)
            return null;
        return new CategoryDto(category.getId(), category.getName());
    }

    public static List<CategoryDto> from(List<Category> categories) {
        if (categories == null || categories.isEmpty())
            return null;

        return categories.stream()
                .map(CategoryDto::from)
                .collect(Collectors.toList());
    }
}