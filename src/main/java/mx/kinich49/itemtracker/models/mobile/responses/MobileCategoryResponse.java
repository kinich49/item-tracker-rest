package mx.kinich49.itemtracker.models.mobile.responses;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Category;

@Data
public class MobileCategoryResponse {

    private final long id;
    private final Long mobileId;
    private final String name;

    public static MobileCategoryResponse from(Category category, Long mobileId) {
        if (category == null)
            return null;
        return new MobileCategoryResponse(category.getId(), mobileId, category.getName());
    }
}
