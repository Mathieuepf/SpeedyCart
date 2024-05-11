package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.model.Shop;
import fr.epf.speedycart.api.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/shop")
    public ResponseEntity<Shop> saveShop(@RequestBody @Valid Shop shop) {
        Shop shopAdded = shopService.saveShopData(shop);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(shopAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/shops")
    public List<Shop> getShops() {
        return shopService.getShopsData();
    }

    @GetMapping("shop/{id}")
    public Shop getShop(@PathVariable long id) {
        return shopService.getShopData(id);
    }

    @GetMapping("shop/{id}/products")
    public List<Product> getProductsFromShop(@PathVariable long id) {
        return shopService.getProductsFromShopData(id);
    }

    @PutMapping("/shop")
    public Shop setShop(@RequestBody @Valid Shop shop) {
        return shopService.setShopData(shop);
    }

    @DeleteMapping("/shop/{id}")
    public void delShop(@PathVariable Long id) {
        shopService.deleteShopData(id);
    }
}
