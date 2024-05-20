package fr.epf.speedycart.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.model.Shop;
import fr.epf.speedycart.api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveProductTest() throws Exception {
        Product product = new Product();
        product.setName("Product");
        product.setUnitPrice(1);
        product.setStock(1);
        product.setWeight(1);
        product.setSizes(1);
        product.setForAdults(true);
        Shop shop = new Shop();
        shop.setId(1L);
        product.setShop(shop);

        when(productService.saveProductData(product)).thenReturn(product);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getProductTest() throws Exception {
        Product product = new Product();
        product.setId(1L);

        when(productService.getProductData(1L)).thenReturn(product);

        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsTest() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);

        when(productService.getProductsData()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    public void setProductTest() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product Update");
        product.setUnitPrice(1);
        product.setStock(1);
        product.setWeight(1);
        product.setSizes(1);
        product.setForAdults(true);
        Shop shop = new Shop();
        shop.setId(1L);
        product.setShop(shop);

        when(productService.updateProductData(product)).thenReturn(product);

        mockMvc.perform(put("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }

    @Test
    public void delProductByIdTest() throws Exception {
        doNothing().when(productService).deleteProductData(1L);

        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProductData(1L);
    }
}
