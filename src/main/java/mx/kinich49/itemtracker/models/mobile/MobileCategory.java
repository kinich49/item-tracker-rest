package mx.kinich49.itemtracker.models.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Category;

import java.util.ArrayList;
import java.util.List;

@Data
public final class MobileCategory {

    private final long id;
    private final String name;

    public static MobileCategory from(Category category) {
        if (category == null)
            return null;

        long id = category.getId();
        String name = category.getName();

        return new MobileCategory(id, name);
    }

    public static List<MobileCategory> from(List<Category> categories) {
        if (categories == null || categories.isEmpty())
            return null;

        List<MobileCategory> mobileCategories = new ArrayList<>(categories.size());
        for (Category category : categories) {
            mobileCategories.add(MobileCategory.from(category));
        }

        return mobileCategories;
    }
}
