package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.ShoppingItemDto;
import mx.kinich49.itemtracker.dtos.ShoppingListDto;
import mx.kinich49.itemtracker.dtos.StoreDto;
import mx.kinich49.itemtracker.models.*;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import mx.kinich49.itemtracker.requests.ShoppingListRequest;
import mx.kinich49.itemtracker.services.impl.ShoppingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingServiceTest {

    private ShoppingService subject;
    @Mock
    ShoppingListRepository shoppingListRepository;
    @Mock
    TransformShoppingListService transformShoppingListService;

    Brand testBrand;
    Category testCategory;
    Store testStore;
    Item testItem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        testBrand = new Brand();
        testBrand.setId(1);
        testBrand.setName("Test Brand");

        testCategory = new Category();
        testCategory.setId(1);
        testCategory.setName("Test Category");

        testStore = new Store();
        testStore.setName("Test Store");
        testStore.setId(1);

        testItem = new Item();
        testItem.setId(1);
        testItem.setName("Test Item");
        testBrand.addItem(testItem);
        testCategory.addItem(testItem);

        subject = new ShoppingServiceImpl(transformShoppingListService, shoppingListRepository);
    }

    @Test
    public void should_create_newDto() {
        //given
        ShoppingListRequest shoppingListRequest = new ShoppingListRequest();
        ShoppingListRequest.Brand brandRequest = new ShoppingListRequest.Brand();
        ShoppingListRequest.Category categoryRequest = new ShoppingListRequest.Category();
        ShoppingListRequest.Store storeRequest = new ShoppingListRequest.Store();
        ShoppingListRequest.ShoppingItem itemRequest = new ShoppingListRequest.ShoppingItem();

        brandRequest.setName("Test Brand");
        categoryRequest.setName("Test Category");
        storeRequest.setName("Test Store");
        itemRequest.setName("Test item");
        itemRequest.setCurrency("MXN");
        itemRequest.setQuantity(1);
        itemRequest.setUnitPrice(100);

        itemRequest.setBrand(brandRequest);
        itemRequest.setCategory(categoryRequest);

        shoppingListRequest.getShoppingItems().add(itemRequest);
        shoppingListRequest.setStore(storeRequest);
        shoppingListRequest.setShoppingDate(LocalDate.now());

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setStore(testStore);
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setItem(testItem);
        shoppingItem.setQuantity(1);
        shoppingItem.setCurrency("MXN");
        shoppingItem.setUnitPrice(100);
        shoppingList.addShoppingItem(shoppingItem);

        StoreDto storeDto = StoreDto.from(testStore);
        ShoppingItemDto itemDto = ShoppingItemDto.from(shoppingItem);

        List<ShoppingItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);
        ShoppingListDto dto = new ShoppingListDto(1, LocalDate.now(),
                storeDto, itemDtos);

        when(transformShoppingListService.from(any(ShoppingListRequest.class)))
                .thenReturn(shoppingList);

        when(shoppingListRepository.save(any(ShoppingList.class)))
                .then(invocation -> {
                    shoppingList.setId(1);
                    return shoppingList;
                });
        //when
        Optional<ShoppingListDto> optDto = subject.save(shoppingListRequest);

        //then
        verify(shoppingListRepository, times(1))
                .save(any(ShoppingList.class));

        verify(transformShoppingListService, times(1))
                .from(any(ShoppingListRequest.class));

        assertTrue(optDto.isPresent());
        ShoppingListDto result = optDto.get();
        assertEquals(dto, result);
    }

    @Test
    public void should_findAndTransform_DtoByDate() {
        //given
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setStore(testStore);
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setItem(testItem);
        shoppingItem.setQuantity(1);
        shoppingItem.setCurrency("MXN");
        shoppingItem.setUnitPrice(100);
        shoppingList.addShoppingItem(shoppingItem);

        LocalDate localDate = LocalDate.now();
        List<ShoppingList> shoppingLists = new ArrayList<>();
        shoppingLists.add(shoppingList);
        when(shoppingListRepository.findByShoppingDate(eq(localDate)))
                .thenReturn(shoppingLists);
        //when
        List<ShoppingListDto> response = subject.findBy(localDate);

        //then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());

        ShoppingListDto listDto = response.get(0);
        List<ShoppingItemDto> itemDtos = listDto.getItems();
        assertNotNull(itemDtos);
        assertFalse(itemDtos.isEmpty());

        ShoppingItemDto itemDto = itemDtos.get(0);

        assertEquals(String.valueOf(shoppingItem.getQuantity()), itemDto.getQuantity());
        assertNotNull(itemDto.getBrand());
        assertEquals(testBrand.getName(), itemDto.getBrand().getName());
        assertNotNull(itemDto.getCategory());
        assertEquals(testCategory.getName(), itemDto.getCategory().getName());
        assertEquals(testItem.getId(), itemDto.getId());

        assertEquals("$1 MXN", itemDto.getUnitPrice());
    }

    @Test
    public void should_findAndTransform_DtoById() {
        //given
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setStore(testStore);
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setItem(testItem);
        shoppingItem.setQuantity(1);
        shoppingItem.setCurrency("MXN");
        shoppingItem.setUnitPrice(100);
        shoppingList.addShoppingItem(shoppingItem);

        when(shoppingListRepository.findById(eq(1L)))
                .thenReturn(Optional.of(shoppingList));
        //when
        Optional<ShoppingListDto> response = subject.findBy(1L);

        //then
        assertNotNull(response);
        assertTrue(response.isPresent());

        ShoppingListDto listDto = response.get();
        List<ShoppingItemDto> itemDtos = listDto.getItems();
        assertNotNull(itemDtos);
        assertFalse(itemDtos.isEmpty());

        ShoppingItemDto itemDto = itemDtos.get(0);

        assertEquals(String.valueOf(shoppingItem.getQuantity()), itemDto.getQuantity());
        assertNotNull(itemDto.getBrand());
        assertEquals(testBrand.getName(), itemDto.getBrand().getName());
        assertNotNull(itemDto.getCategory());
        assertEquals(testCategory.getName(), itemDto.getCategory().getName());
        assertEquals(testItem.getId(), itemDto.getId());
        assertEquals("$1 MXN", itemDto.getUnitPrice());
    }

    @Test
    public void should_notFind_byDate() {
        //given
        when(shoppingListRepository.findByShoppingDate(any(LocalDate.class)))
                .thenReturn(Collections.emptyList());

        //when
        List<ShoppingListDto> response = subject.findBy(LocalDate.now());

        //then
        assertNotNull(response);
        assertTrue(response.isEmpty());

    }

    @Test
    public void should_notFind_byId() {
        //given
        when(shoppingListRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        //when
        Optional<ShoppingListDto> response = subject.findBy(1);

        //then
        assertFalse(response.isPresent());

    }
}
