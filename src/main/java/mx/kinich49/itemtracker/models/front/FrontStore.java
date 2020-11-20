package mx.kinich49.itemtracker.models.front;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Store;

import java.util.List;
import java.util.stream.Collectors;

@Data
public final class FrontStore {

    public final long id;
    public final String name;

    public static FrontStore from(Store store) {
        if (store == null)
            return null;
        return new FrontStore(store.getId(), store.getName());
    }

    public static List<FrontStore> from(List<Store> stores) {
        if (stores == null || stores.isEmpty())
            return null;

        return stores.stream()
                .map(FrontStore::from)
                .collect(Collectors.toList());
    }

}