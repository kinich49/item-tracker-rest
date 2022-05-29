package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.MockUserDetails;
import mx.kinich49.itemtracker.models.database.*;
import mx.kinich49.itemtracker.models.front.FrontShoppingItem;
import mx.kinich49.itemtracker.models.front.FrontShoppingList;
import mx.kinich49.itemtracker.models.front.FrontStore;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import mx.kinich49.itemtracker.requests.main.*;
import mx.kinich49.itemtracker.services.impl.ShoppingServiceImpl;
import mx.kinich49.itemtracker.validators.shoppinglist.ShoppingListOwnershipParameter;
import mx.kinich49.itemtracker.validators.shoppinglist.ShoppingListOwnershipValidator;
import mx.kinich49.itemtracker.validators.shoppinglist.ShoppingListParameter;
import mx.kinich49.itemtracker.validators.shoppinglist.ShoppingListValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

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
    @Mock
    ShoppingListValidator shoppingListValidator;
    @Mock
    ShoppingListOwnershipValidator ownershipParameter;

    Brand testBrand;
    Category testCategory;
    Store testStore;
    Item testItem;

    @BeforeEach
    public void setup() {

        testBrand = new Brand();
        testBrand.setId(1L);
        testBrand.setName("Test Brand");

        testCategory = new Category();
        testCategory.setId(1L);
        testCategory.setName("Test Category");

        testStore = new Store();
        testStore.setName("Test Store");
        testStore.setId(1L);

        testItem = new Item();
        testItem.setId(1L);
        testItem.setName("Test Item");
        testBrand.addItem(testItem);
        testCategory.addItem(testItem);
    }

    @Test
    @DisplayName("Should create new DTO")
    public void should_create_newDto() throws BusinessException {
        //given
        MainShoppingListRequest request = new MainShoppingListRequest();
        BrandRequest brandRequest = new BrandRequest();
        CategoryRequest categoryRequest = new CategoryRequest();
        StoreRequest storeRequest = new StoreRequest();
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();

        UserDetails mockUserDetails = new MockUserDetails("Test username");

        brandRequest.setName("Test Brand");
        categoryRequest.setName("Test Category");
        storeRequest.setName("Test Store");
        itemRequest.setName("Test item");
        itemRequest.setCurrency("MXN");
        itemRequest.setQuantity(1);
        itemRequest.setUnitPrice(100);
        itemRequest.setUnit("Unit");

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

        FrontStore frontStore = FrontStore.from(testStore);
        FrontShoppingItem itemDto = FrontShoppingItem.from(shoppingItem);

        List<FrontShoppingItem> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);
        FrontShoppingList dto = new FrontShoppingList(1, LocalDate.now(),
                frontStore, itemDtos);

        when(dtoEntityService.from(any(MainShoppingListRequest.class),
                any(UserDetails.class)))
                .thenReturn(shoppingList);

        when(shoppingListRepository.save(any(ShoppingList.class)))
                .then(invocation -> {
                    shoppingList.setId(1L);
                    return shoppingList;
                });

        //when
        Optional<FrontShoppingList> optDto = subject.save(request, mockUserDetails);

        //then
        verify(shoppingListRepository, times(1))
                .save(any(ShoppingList.class));

        verify(dtoEntityService, times(1))
                .from(any(MainShoppingListRequest.class), eq(mockUserDetails));

        assertTrue(optDto.isPresent());
        FrontShoppingList result = optDto.get();
        assertEquals(dto, result);
    }

    @Test
    public void should_findAndTransform_DtoByDate() {
        //given
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(1L);
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

        var mockUserDetails = new MockUserDetails("Test username");

        when(shoppingListRepository.findByShoppingDateAndUsername(eq(localDate), eq("Test username")))
                .thenReturn(shoppingLists);


        //when
        List<FrontShoppingList> response = subject.findBy(localDate, mockUserDetails);

        //then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());

        FrontShoppingList listDto = response.get(0);
        List<FrontShoppingItem> itemDtos = listDto.getItems();
        assertNotNull(itemDtos);
        assertFalse(itemDtos.isEmpty());

        FrontShoppingItem itemDto = itemDtos.get(0);

        assertEquals("1", itemDto.getQuantity());
        assertNotNull(itemDto.getBrand());
        assertEquals(testBrand.getName(), itemDto.getBrand().getName());
        assertNotNull(itemDto.getCategory());
        assertEquals(testCategory.getName(), itemDto.getCategory().getName());
        assertEquals(testItem.getId(), itemDto.getId());

        assertEquals("$1.00 MXN", itemDto.getUnitPrice());
    }

    @Test
    @DisplayName("Should find and transform DTO by ID")
    public void should_findAndTransform_DtoById() throws BusinessException {
        //given
        long shoppingListId = 1L;
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(shoppingListId);
        shoppingList.setStore(testStore);
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setItem(testItem);
        shoppingItem.setQuantity(1);
        shoppingItem.setCurrency("MXN");
        shoppingItem.setUnitPrice(100);
        shoppingList.addShoppingItem(shoppingItem);


        var userDetails = new MockUserDetails("Test username");
        when(shoppingListRepository.findById(eq(shoppingListId)))
                .thenReturn(Optional.of(shoppingList));
        //when
        Optional<FrontShoppingList> response = subject.findBy(shoppingListId, userDetails);

        //then
        assertNotNull(response);
        assertTrue(response.isPresent());

        FrontShoppingList listDto = response.get();
        List<FrontShoppingItem> itemDtos = listDto.getItems();
        assertNotNull(itemDtos);
        assertFalse(itemDtos.isEmpty());

        FrontShoppingItem itemDto = itemDtos.get(0);

        assertEquals("1", itemDto.getQuantity());
        assertNotNull(itemDto.getBrand());
        assertEquals(testBrand.getName(), itemDto.getBrand().getName());
        assertNotNull(itemDto.getCategory());
        assertEquals(testCategory.getName(), itemDto.getCategory().getName());
        assertEquals(testItem.getId(), itemDto.getId());
        assertEquals("$1.00 MXN", itemDto.getUnitPrice());
    }

    @Test
    @DisplayName("Should not find shopping list by date")
    public void should_notFind_byDate() {
        //given

        var userDetails = new MockUserDetails("Test username");
        var now = LocalDate.now();

        when(shoppingListRepository.findByShoppingDateAndUsername(eq(now), eq(userDetails.getUsername())))
                .thenReturn(null);

        //when
        List<FrontShoppingList> response = subject.findBy(LocalDate.now(), userDetails);

        //then
        assertNull(response);

    }

    @Test
    @DisplayName("Should not find by shopping list ID")
    public void should_notFind_byId() throws BusinessException{
        //given
        long shoppingListId = 1L;
        var userDetails = new MockUserDetails("Test username");

        when(shoppingListRepository.findById(eq(shoppingListId)))
                .thenReturn(Optional.empty());

        //when
        Optional<FrontShoppingList> response = subject.findBy(shoppingListId, userDetails);

        //then
        assertFalse(response.isPresent());

    }

}
