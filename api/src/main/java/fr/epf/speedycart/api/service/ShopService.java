package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.model.Shop;

import java.util.List;

public interface ShopService {
    Shop saveShopData(Shop shop);

    Shop setShopData(Shop shop);

    List<Shop> getShopsData();

    Shop getShopData(Long Id);

    List<Product> getProductsFromShopData(Long Id);

    void deleteShopData(Long Id);
}
