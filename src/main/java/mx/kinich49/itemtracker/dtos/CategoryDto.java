package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import mx.kinich49.itemtracker.models.Category;

@Data
public class CategoryDto {

    private final long id;
    private final String name;

    public static CategoryDto from(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}