package fr.epf.speedycart.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epf.speedycart.api.model.Address;
import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.model.Shop;
import fr.epf.speedycart.api.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopService shopService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveShopTest() throws Exception {
        Shop shop = new Shop();
        shop.setName("Valid Shop");
        shop.setSiret("12345678901234");
        Address address = new Address();
        address.setId(1L);
        address.setNumber("123");
        address.setRoad("rue des flowers");
        address.setCity("Paris");
        shop.setAddress(address);

        when(shopService.saveShopData(shop)).thenReturn(shop);

        mockMvc.perform(post("/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shop)))
                .andExpect(status().isCreated());
    }

    @Test
    public void saveShopInvalidTest() throws Exception {
        Shop shop = new Shop();
        shop.setName("Invalid Shop");
        shop.setSiret("123456789");

        mockMvc.perform(post("/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shop)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getShopsTest() throws Exception {
        Shop shop1 = new Shop();
        shop1.setId(0L);
        Shop shop2 = new Shop();
        shop2.setId(0L);
        List<Shop> shops = Arrays.asList(shop1, shop2);
        when(shopService.getShopsData()).thenReturn(shops);

        mockMvc.perform(get("/shops"))
                .andExpect(status().isOk());
    }

    @Test
    public void getShopTest() throws Exception {
        Shop shop = new Shop();
        shop.setId(1L);
        when(shopService.getShopData(1L)).thenReturn(shop);

        mockMvc.perform(get("/shop/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsFromShopTest() throws Exception {
        Product product1 = new Product();
        product1.setId(0L);
        Product product2 = new Product();
        product2.setId(0L);
        List<Product> products = Arrays.asList(product1, product2);
        when(shopService.getProductsFromShopData(1L)).thenReturn(products);

        mockMvc.perform(get("/shop/1/products"))
                .andExpect(status().isOk());
    }

    @Test
    public void setShopTest() throws Exception {
        Shop shop = new Shop();
        shop.setId(1L);
        shop.setName("Updated Shop");
        shop.setDescription("Updated description");
        shop.setActiveSince(LocalDateTime.now().minusDays(1));
        shop.setSiret("12345678901234");
        shop.setAddress(new Address());

        when(shopService.setShopData(any(Shop.class))).thenReturn(shop);

        mockMvc.perform(put("/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shop)))
                .andExpect(status().isOk());
    }

    @Test
    public void delShopTest() throws Exception {
        doNothing().when(shopService).deleteShopData(1L);

        mockMvc.perform(delete("/shop/1"))
                .andExpect(status().isOk());

        verify(shopService, times(1)).deleteShopData(1L);
    }
}
