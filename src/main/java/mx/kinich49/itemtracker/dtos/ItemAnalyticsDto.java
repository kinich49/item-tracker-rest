package mx.kinich49.itemtracker.dtos;

import lombok.Data;

@Data
public class ItemAnalyticsDto {

    private String latestStore;
    private String latestDate;
    private String latestPrice;
    private String averagePrice;
}
