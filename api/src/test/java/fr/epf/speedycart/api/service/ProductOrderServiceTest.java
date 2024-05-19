package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.repository.ProductOrderDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductOrderServiceTest {

    @InjectMocks
    private ProductOrderServiceImpl productOrderService;

    @Mock
    private ProductOrderDao productOrderDao;

    @Test
    public void existsByProductDataTest() {
        Product product = new Product();
        product.setId(0L);

        when(productOrderDao.existsByProduct(product)).thenReturn(true);
        boolean exists = productOrderService.existsByProductData(product);
        assertTrue(exists);
    }

    @Test
    public void notExistsByProductDataTest() {
        Product product = new Product();
        product.setId(0L);

        when(productOrderDao.existsByProduct(product)).thenReturn(false);
        boolean exists = productOrderService.existsByProductData(product);
        assertFalse(exists);
    }
}
