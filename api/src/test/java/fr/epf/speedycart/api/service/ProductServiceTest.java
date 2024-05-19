package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.ProductNotFoundException;
import fr.epf.speedycart.api.exception.ShopNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDao productDao;

    @Mock
    private ProductOrderService productOrderService;

    @Mock
    private ShopDao shopDao;

    @Test
    public void saveProductDataTest() {
        Product product = new Product();
        product.setId(0L);
        Shop shop = new Shop();
        shop.setId(0L);
        product.setShop(shop);

        when(shopDao.findById(shop.getId())).thenReturn(Optional.of(shop));
        when(productDao.save(product)).thenReturn(product);

        Product saved = productService.saveProductData(product);
        assertEquals(saved, product);
    }

    @Test
    public void saveProductDataShopNotFoundTest() {
        Product product = new Product();
        product.setId(0L);
        Shop shop = new Shop();
        shop.setId(0L);
        product.setShop(shop);

        when(shopDao.findById(shop.getId())).thenReturn(Optional.empty());
        assertThrows(ShopNotFoundException.class, () -> productService.saveProductData(product));
    }

    @Test
    public void getProductsDataTest() {
        Product product1 = new Product();
        product1.setId(0L);
        Product product2 = new Product();
        product2.setId(0L);
        List<Product> products = Arrays.asList(product1, product2);

        when(productDao.findAll()).thenReturn(products);
        List<Product> returnedProducts = productService.getProductsData();
        assertEquals(returnedProducts, products);
    }

    @Test
    public void getProductsDataNotFoundTest() {
        when(productDao.findAll()).thenReturn(new ArrayList<>());
        assertThrows(ProductNotFoundException.class, () -> productService.getProductsData());
    }

    @Test
    public void getProductDataTest() {
        Long id = 0L;
        Product product = new Product();
        product.setId(id);

        when(productDao.findById(id)).thenReturn(Optional.of(product));
        Product found = productService.getProductData(id);
        assertEquals(found, product);
    }

    @Test
    public void getProductDataNotFoundTest() {
        Long id = 0L;

        when(productDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.getProductData(id));
    }

    @Test
    public void updateProductDataTest() {
        Product product = new Product();
        product.setId(0L);
        Shop shop = new Shop();
        shop.setId(0L);
        product.setShop(shop);

        when(productDao.findById(product.getId())).thenReturn(Optional.of(product));
        when(shopDao.findById(shop.getId())).thenReturn(Optional.of(shop));
        when(productDao.save(product)).thenReturn(product);

        Product updated = productService.updateProductData(product);
        assertEquals(updated, product);
    }

    @Test
    public void updateProductDataNotFoundTest() {
        Product product = new Product();
        product.setId(0L);

        when(productDao.findById(product.getId())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.updateProductData(product));
    }

    @Test
    public void updateProductDataShopNotFoundTest() {
        Product product = new Product();
        product.setId(0L);
        Shop shop = new Shop();
        shop.setId(0L);
        product.setShop(shop);

        when(productDao.findById(product.getId())).thenReturn(Optional.of(product));
        when(shopDao.findById(shop.getId())).thenReturn(Optional.empty());
        assertThrows(ShopNotFoundException.class, () -> productService.updateProductData(product));
    }

    @Test
    public void deleteProductDataTest() {
        Long id = 0L;
        Product product = new Product();
        product.setId(id);
        product.setDisableSince(null);

        when(productDao.findById(id)).thenReturn(Optional.of(product));
        when(productOrderService.existsByProductData(product)).thenReturn(false);

        productService.deleteProductData(id);
        verify(productDao, times(1)).delete(product);
    }

    @Test
    public void deleteProductDataNotFoundTest() {
        Long id = 0L;

        when(productDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProductData(id));
    }

    @Test
    public void deleteProductDataOrderFoundTest() {
        Long id = 0L;
        Product product = new Product();
        product.setId(id);
        product.setDisableSince(null);

        when(productDao.findById(id)).thenReturn(Optional.of(product));
        when(productOrderService.existsByProductData(product)).thenReturn(true);

        productService.deleteProductData(id);
        assertEquals(product.getDisableSince().getDayOfYear(), LocalDateTime.now().plusMinutes(5).getDayOfYear());
    }
}
