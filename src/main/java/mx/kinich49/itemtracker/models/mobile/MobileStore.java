package mx.kinich49.itemtracker.models.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Store;

import java.util.ArrayList;
import java.util.List;

@Data
public final class MobileStore {

    private final long id;
    private final String name;

    public static MobileStore from(Store store) {
        if (store == null)
            return null;

        long id = store.getId();
        String name = store.getName();

        return new MobileStore(id, name);
    }

    public static List<MobileStore> from(List<Store> stores) {
        if (stores == null || stores.isEmpty())
            return null;

        List<MobileStore> mobileStores = new ArrayList<>(stores.size());
        for (Store store : stores) {
            mobileStores.add(MobileStore.from(store));
        }

        return mobileStores;
    }
}
