package mx.kinich49.itemtracker.models.mobile.responses;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Store;

@Data
public final class MobileStoreResponse {

    private final long id;
    private final Long mobileId;
    private final String name;

    public static MobileStoreResponse from(Store store, Long mobileId) {
        if (store == null)
            return null;

        return new MobileStoreResponse(store.getId(), mobileId, store.getName());
    }
}
