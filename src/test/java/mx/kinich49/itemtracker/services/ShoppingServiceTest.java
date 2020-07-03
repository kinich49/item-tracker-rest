package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.ShoppingItemDto;
import mx.kinich49.itemtracker.dtos.ShoppingListDto;
import mx.kinich49.itemtracker.dtos.StoreDto;
import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.*;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import mx.kinich49.itemtracker.requests.ShoppingListRequest;
import mx.kinich49.itemtracker.services.impl.ShoppingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @InjectMocks
    private ShoppingServiceImpl subject;
    @Mock
    ShoppingListRepository shoppingListRepository;
    @Mock
    DtoEntityService dtoEntityService;

    Brand testBrand;
    Category testCategory;
    Store testStore;
    Item testItem;

    @BeforeEach
    public void setup() {

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
    }

    @Test()
    @DisplayName("Should return an exception when user does not exists")
    public void should_throwException_when_userDoesNotExist() {
        //given
        ShoppingListRequest request = new ShoppingListRequest();
        ShoppingListRequest.Brand brandRequest = new ShoppingListRequest.Brand();
        ShoppingListRequest.Category categoryRequest = new ShoppingListRequest.Category();
        ShoppingListRequest.Store storeRequest = new ShoppingListRequest.Store();
        ShoppingListRequest.ShoppingItem itemRequest = new ShoppingListRequest.ShoppingItem();

        request.setUserId(Long.MAX_VALUE);
        brandRequest.setName("Test Brand");
        categoryRequest.setName("Test Category");
        storeRequest.setName("Test Store");
        itemRequest.setName("Test item");
        itemRequest.setCurrency("MXN");
        itemRequest.setQuantity(1);
        itemRequest.setUnitPrice(100);

        itemRequest.setBrand(brandRequest);
        itemRequest.setCategory(categoryRequest);

        request.getShoppingItems().add(itemRequest);
        request.setStore(storeRequest);
        request.setShoppingDate(LocalDate.now());

        //when
        Exception exception = assertThrows(UserNotFoundException.class,
                () -> {
                    when(dtoEntityService.from(any(ShoppingListRequest.class)))
                            .thenThrow(new UserNotFoundException(Long.MAX_VALUE));
                    subject.save(request);
                });

        assertEquals(String.format("User with id %d not found", Long.MAX_VALUE),
                exception.getMessage());
    }

    @Test
    public void should_create_newDto() throws UserNotFoundException {
        //given
        ShoppingListRequest request = new ShoppingListRequest();
        ShoppingListRequest.Brand brandRequest = new ShoppingListRequest.Brand();
        ShoppingListRequest.Category categoryRequest = new ShoppingListRequest.Category();
        ShoppingListRequest.Store storeRequest = new ShoppingListRequest.Store();
        ShoppingListRequest.ShoppingItem itemRequest = new ShoppingListRequest.ShoppingItem();

        request.setUserId(1L);
        brandRequest.setName("Test Brand");
        categoryRequest.setName("Test Category");
        storeRequest.setName("Test Store");
        itemRequest.setName("Test item");
        itemRequest.setCurrency("MXN");
        itemRequest.setQuantity(1);
        itemRequest.setUnitPrice(100);

        itemRequest.setBrand(brandRequest);
        itemRequest.setCategory(categoryRequest);

        request.getShoppingItems().add(itemRequest);
        request.setStore(storeRequest);
        request.setShoppingDate(LocalDate.now());

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

        when(dtoEntityService.from(any(ShoppingListRequest.class)))
                .thenReturn(shoppingList);

        when(shoppingListRepository.save(any(ShoppingList.class)))
                .then(invocation -> {
                    shoppingList.setId(1);
                    return shoppingList;
                });
        //when
        Optional<ShoppingListDto> optDto = subject.save(request);

        //then
        verify(shoppingListRepository, times(1))
                .save(any(ShoppingList.class));

        verify(dtoEntityService, times(1))
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
        when(shoppingListRepository.findByShoppingDateAndUserId(eq(localDate), eq(1L)))
                .thenReturn(shoppingLists);
        //when
        List<ShoppingListDto> response = subject.findBy(localDate, 1L);

        //then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());

        ShoppingListDto listDto = response.get(0);
        List<ShoppingItemDto> itemDtos = listDto.getItems();
        assertNotNull(itemDtos);
        assertFalse(itemDtos.isEmpty());

        ShoppingItemDto itemDto = itemDtos.get(0);

        assertEquals("1", itemDto.getQuantity());
        assertNotNull(itemDto.getBrand());
        assertEquals(testBrand.getName(), itemDto.getBrand().getName());
        assertNotNull(itemDto.getCategory());
        assertEquals(testCategory.getName(), itemDto.getCategory().getName());
        assertEquals(testItem.getId(), itemDto.getId());

        assertEquals("$1.00 MXN", itemDto.getUnitPrice());
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

        when(shoppingListRepository.findByIdAndUserId(eq(1L), eq(1L)))
                .thenReturn(Optional.of(shoppingList));
        //when
        Optional<ShoppingListDto> response = subject.findBy(1L, 1L);

        //then
        assertNotNull(response);
        assertTrue(response.isPresent());

        ShoppingListDto listDto = response.get();
        List<ShoppingItemDto> itemDtos = listDto.getItems();
        assertNotNull(itemDtos);
        assertFalse(itemDtos.isEmpty());

        ShoppingItemDto itemDto = itemDtos.get(0);

        assertEquals("1", itemDto.getQuantity());
        assertNotNull(itemDto.getBrand());
        assertEquals(testBrand.getName(), itemDto.getBrand().getName());
        assertNotNull(itemDto.getCategory());
        assertEquals(testCategory.getName(), itemDto.getCategory().getName());
        assertEquals(testItem.getId(), itemDto.getId());
        assertEquals("$1.00 MXN", itemDto.getUnitPrice());
    }

    @Test
    public void should_notFind_byDate() {
        //given
        when(shoppingListRepository.findByShoppingDateAndUserId(any(LocalDate.class), eq(1L)))
                .thenReturn(Collections.emptyList());

        //when
        List<ShoppingListDto> response = subject.findBy(LocalDate.now(), 1L);

        //then
        assertNotNull(response);
        assertTrue(response.isEmpty());

    }

    @Test
    public void should_notFind_byId() {
        //given
        when(shoppingListRepository.findByIdAndUserId(anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        //when
        Optional<ShoppingListDto> response = subject.findBy(1L, 1L);

        //then
        assertFalse(response.isPresent());

    }
}
