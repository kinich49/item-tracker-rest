package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import mx.kinich49.itemtracker.models.Store;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StoreDto {

    public final long id;
    public final String name;

    public static StoreDto from(Store store) {
        if (store == null)
            return null;
        return new StoreDto(store.getId(), store.getName());
    }

    public static List<StoreDto> from(List<Store> stores) {
        if (stores == null || stores.isEmpty())
            return null;

        return stores.stream()
                .map(StoreDto::from)
                .collect(Collectors.toList());
    }

}