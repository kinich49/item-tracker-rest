package mx.kinich49.itemtracker.models.front;

import mx.kinich49.itemtracker.models.database.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FrontStoreTest {

    @Test
    @DisplayName("Store Dto must be null")
    public void shouldReturnNull() {
        //given
        Store store = null;
        //then
        assertNull(FrontStore.from(store));
        //given
        List<Store> stores = null;
        //then
        assertNull(FrontStore.from(stores));
        //given
        stores = Collections.emptyList();
        //then
        assertNull(FrontStore.from(stores));
    }

    @Test
    @DisplayName("Should return Store Dto")
    public void shouldReturnStoreDto() {
        //given
        Store store = new Store();
        store.setName("Test store");
        store.setId(1L);

        //when
        FrontStore dto = FrontStore.from(store);
        assertNotNull(dto);
        assertEquals(store.getName(), dto.getName());
        assertEquals(store.getId(), dto.getId());
    }

    @Test
    @DisplayName("Should return list of Store Dtos")
    public void shouldReturnListOfDtos() {
        //given
        Store storeA = new Store();
        storeA.setName("Test Store A");
        storeA.setId(1L);

        Store storeB = new Store();
        storeB.setName("Test Store B");
        storeB.setId(2L);

        Store storeC = new Store();
        storeC.setName("Test Store C");
        storeC.setId(3L);

        List<Store> stores = new ArrayList<>();
        stores.add(storeA);
        stores.add(storeB);
        stores.add(storeC);

        //when
        List<FrontStore> dtos = FrontStore.from(stores);

        //then
        assertNotNull(dtos);
        assertFalse(dtos.isEmpty());
        assertEquals(3, dtos.size());
        assertDto(storeA, dtos.get(0));
        assertDto(storeB, dtos.get(1));
        assertDto(storeC, dtos.get(2));
    }

    private void assertDto(Store expected, FrontStore actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getId(), actual.getId());
    }
}
