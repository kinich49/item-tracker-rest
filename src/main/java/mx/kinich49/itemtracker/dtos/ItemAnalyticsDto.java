package mx.kinich49.itemtracker.dtos;

import lombok.Builder;
import lombok.Data;
import mx.kinich49.itemtracker.models.front.FrontItem;

@Data
@Builder
public final class ItemAnalyticsDto {

    private final FrontItem item;
    private final String latestStore;
    private final String latestDate;
    private final String latestPrice;
    private final String averagePrice;
}
