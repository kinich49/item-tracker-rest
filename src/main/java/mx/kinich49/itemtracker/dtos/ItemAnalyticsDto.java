package mx.kinich49.itemtracker.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ItemAnalyticsDto {

    private final ItemDto item;
    private final String latestStore;
    private final String latestDate;
    private final String latestPrice;
    private final String averagePrice;
}
