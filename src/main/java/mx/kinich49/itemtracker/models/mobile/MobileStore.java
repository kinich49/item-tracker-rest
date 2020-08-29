package mx.kinich49.itemtracker.models.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Store;

import java.util.List;
import java.util.stream.Collectors;

@Data
public final class MobileStore {

    private final long remoteId;
    private final Long mobileId;
    private final String name;

    public static MobileStore from(Store store, Long mobileId) {
        if (store == null)
            return null;

        return new MobileStore(store.getId(), mobileId, store.getName());
    }

    public static MobileStore from(Store store) {
        if (store == null)
            return null;

        return new MobileStore(store.getId(), null, store.getName());
    }

    public static List<MobileStore> from(List<Store> stores) {
        if (stores == null || stores.isEmpty())
            return null;

        return stores.stream()
                .map(MobileStore::from)
                .collect(Collectors.toList());
    }
}
