package mx.kinich49.itemtracker.models.front;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Category;

import java.util.List;
import java.util.stream.Collectors;

@Data
public final class FrontCategory {

    private final long id;
    private final String name;

    public static FrontCategory from(Category category) {
        if (category == null)
            return null;
        return new FrontCategory(category.getId(), category.getName());
    }

    public static List<FrontCategory> from(List<Category> categories) {
        if (categories == null || categories.isEmpty())
            return null;

        return categories.stream()
                .map(FrontCategory::from)
                .collect(Collectors.toList());
    }
}