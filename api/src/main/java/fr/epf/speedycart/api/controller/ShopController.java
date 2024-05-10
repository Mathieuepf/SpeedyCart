package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.repository.ShopDao;
import fr.epf.speedycart.api.model.Shop;
import fr.epf.speedycart.api.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shops")
    public List<Shop> getShops() {
        return shopService.getShopsData();
    }

    @GetMapping("shop/{id}")
    public Shop getShop(@PathVariable long id) {
        return shopService.getShopData(id);
    }

    @GetMapping("shop/{id}/products")
    public List<Product> getProductsFromShop(@PathVariable long id){
        return shopService.getProductsFromShopData(id);
    }
}
