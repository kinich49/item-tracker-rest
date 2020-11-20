package mx.kinich49.itemtracker.dtos;

import lombok.Builder;
import lombok.Data;
import mx.kinich49.itemtracker.models.front.FrontBrand;
import mx.kinich49.itemtracker.models.front.FrontCategory;
import mx.kinich49.itemtracker.models.front.FrontItem;

import java.util.List;

@Data
@Builder
public final class SuggestionsDto {

    private final List<FrontCategory> categories;
    private final List<FrontBrand> brands;
    private final List<FrontItem> items;
}
