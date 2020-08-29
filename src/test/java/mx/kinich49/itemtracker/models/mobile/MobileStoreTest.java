package mx.kinich49.itemtracker.models.mobile;

import mx.kinich49.itemtracker.models.database.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MobileStoreTest {

    @Test
    @DisplayName("Should Return a store response with null mobile id")
    public void shouldReturnStoreResponse() {
        //Given
        Store store = new Store();
        store.setName("Test Store");
        store.setId(23L);

        //when
        MobileStore response = MobileStore.from(store, null);

        //then
        assertNotNull(response);
        assertEquals(store.getName(), response.getName());
        assertEquals(store.getId(), response.getRemoteId());
        assertNull(response.getMobileId());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    @DisplayName("Should return null response")
    public void shouldReturnNullResponse() {
        //when
        MobileStore response = MobileStore.from(null, null);

        //then
        assertNull(response);
    }

    @Test
    @DisplayName("Should return a store response with mobile id")
    public void shouldReturnStoreResponseWithMobileId() {
        //given
        Store store = new Store();
        store.setId(10L);
        store.setName("Test Store");
        Long mobileId = 1L;

        //when
        MobileStore response = MobileStore.from(store, mobileId);

        //then
        assertNotNull(response);
        assertEquals(store.getId(), response.getRemoteId());
        assertEquals(store.getName(), response.getName());
        assertEquals(mobileId, response.getMobileId());
    }
}
