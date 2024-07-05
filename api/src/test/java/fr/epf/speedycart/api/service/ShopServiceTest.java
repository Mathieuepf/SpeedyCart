package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.ProductNotFoundException;
import fr.epf.speedycart.api.exception.ShopNotFoundException;
import fr.epf.speedycart.api.exception.UserException;
import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.Address;
import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.model.Shop;
import fr.epf.speedycart.api.repository.ProductDao;
import fr.epf.speedycart.api.repository.ShopDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {

    @InjectMocks
    private ShopServiceImpl shopService;

    @Mock
    private ShopDao shopDao;

    @Mock
    private AddressService addressService;

    @Mock
    private ProductDao productDao;

    @Test
    public void saveShopDataTest() {
        Shop shop = new Shop();
        shop.setId(0L);
        Address address = new Address();
        address.setId(0L);
        shop.setAddress(address);

        when(addressService.getAddressData(shop.getAddress().getId())).thenReturn(address);
        when(shopDao.save(shop)).thenReturn(shop);

        Shop saved = shopService.saveShopData(shop);
        assertEquals(saved, shop);
    }

    @Test
    public void getShopsDataTest() {
        Shop shop1 = new Shop();
        shop1.setId(0L);
        Shop shop2 = new Shop();
        shop2.setId(0L);
        List<Shop> shops = Arrays.asList(shop1, shop2);

        when(shopDao.findAll()).thenReturn(shops);
        List<Shop> returnedShops = shopService.getShopsData();
        assertEquals(returnedShops, shops);
    }

    @Test
    public void getShopsDataNotFoundTest() {
        when(shopDao.findAll()).thenReturn(new ArrayList<>());
        assertThrows(ShopNotFoundException.class, () -> shopService.getShopsData());
    }

    @Test
    public void getShopDataTest() {
        Long id = 0L;
        Shop shop = new Shop();
        shop.setId(id);

        when(shopDao.findById(id)).thenReturn(Optional.of(shop));
        Shop found = shopService.getShopData(id);
        assertEquals(found, shop);
    }

    @Test
    public void getShopDataNotFoundTest() {
        Long id = 0L;

        when(shopDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(ShopNotFoundException.class, () -> shopService.getShopData(id));
    }

    @Test
    public void getProductsFromShopDataTest() {
        Long id = 0L;
        Shop shop = new Shop();
        Product product1 = new Product();
        product1.setId(0L);
        Product product2 = new Product();
        product2.setId(0L);
        List<Product> products = Arrays.asList(product1, product2);

        when(shopDao.findById(id)).thenReturn(Optional.of(shop));
        when(productDao.findByShopId(id)).thenReturn(products);
        List<Product> returnedProducts = shopService.getProductsFromShopData(id);
        assertEquals(returnedProducts, products);
    }

    @Test
    public void getProductsFromShopDataNotFoundTest() {
        Long id = 0L;
        Shop shop = new Shop();

        when(shopDao.findById(id)).thenReturn(Optional.of(shop));
        when(productDao.findByShopId(id)).thenReturn(new ArrayList<>());
        assertThrows(ProductNotFoundException.class, () -> shopService.getProductsFromShopData(id));
    }

    @Test
    public void deleteShopDataTest() {
        Long id = 0L;
        Shop shop = new Shop();
        shop.setId(id);

        when(shopDao.findById(id)).thenReturn(Optional.of(shop));
        shopService.deleteShopData(id);

        verify(shopDao, times(1)).findById(id);
        verify(shopDao, times(1)).save(shop);
        assertNotNull(shop.getDisableSince());
    }

    @Test
    public void deleteShopDataAlreadyDisabledTest() {
        Long id = 0L;
        Shop shop = new Shop();
        shop.setId(id);
        shop.setDisableSince(LocalDateTime.now().minusDays(1));

        when(shopDao.findById(id)).thenReturn(Optional.of(shop));
        assertThrows(UserException.class, () -> shopService.deleteShopData(id));
    }

    @Test
    public void deleteShopDataShopNotFoundTest() {
        Long id = 0L;

        when(shopDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(ShopNotFoundException.class, () -> shopService.deleteShopData(id));
    }
}
