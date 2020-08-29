package mx.kinich49.itemtracker.models.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Category;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MobileCategory {

    private final long remoteId;
    private final Long mobileId;
    private final String name;

    public static MobileCategory from(Category category, Long mobileId) {
        if (category == null)
            return null;
        return new MobileCategory(category.getId(), mobileId, category.getName());
    }

    public static MobileCategory from(Category category) {
        if (category == null)
            return null;

        return new MobileCategory(category.getId(), null, category.getName());
    }

    public static List<MobileCategory> from(List<Category> categories) {
        if (categories == null || categories.isEmpty())
            return null;

        return categories.stream()
                .map(MobileCategory::from)
                .collect(Collectors.toList());
    }
}
