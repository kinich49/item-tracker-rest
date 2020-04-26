package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import mx.kinich49.itemtracker.models.Store;

@Data
public class StoreDto {

    public final long id;
    public final String name;

    public static StoreDto from(Store store) {
        return new StoreDto(store.getId(), store.getName());
    }

}