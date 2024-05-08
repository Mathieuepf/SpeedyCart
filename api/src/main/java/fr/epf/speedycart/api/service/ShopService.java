package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.model.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    List<Shop> getShopsData();
    Optional<Shop> getShopData(Long Id);
    List<Product> getProductsFromShopData (Long Id);
}
