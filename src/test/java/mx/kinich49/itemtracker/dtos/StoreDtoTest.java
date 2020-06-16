package mx.kinich49.itemtracker.dtos;

import mx.kinich49.itemtracker.models.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoreDtoTest {

    @Test
    @DisplayName("Store Dto must be null")
    public void shouldReturnNull() {
        //given
        Store store = null;
        //then
        assertNull(StoreDto.from(store));
        //given
        List<Store> stores = null;
        //then
        assertNull(StoreDto.from(stores));
        //given
        stores = Collections.emptyList();
        //then
        assertNull(StoreDto.from(stores));
    }

    @Test
    @DisplayName("Should return Store Dto")
    public void shouldReturnStoreDto() {
        //given
        Store store = new Store();
        store.setName("Test store");
        store.setId(1);

        //when
        StoreDto dto = StoreDto.from(store);
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
        storeA.setId(1);

        Store storeB = new Store();
        storeB.setName("Test Store B");
        storeB.setId(2);

        Store storeC = new Store();
        storeC.setName("Test Store C");
        storeC.setId(3);

        List<Store> stores = new ArrayList<>();
        stores.add(storeA);
        stores.add(storeB);
        stores.add(storeC);

        //when
        List<StoreDto> dtos = StoreDto.from(stores);

        //then
        assertNotNull(dtos);
        assertFalse(dtos.isEmpty());
        assertEquals(3, dtos.size());
        assertDto(storeA, dtos.get(0));
        assertDto(storeB, dtos.get(1));
        assertDto(storeC, dtos.get(2));
    }

    private void assertDto(Store expected, StoreDto actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getId(), actual.getId());
    }
}
