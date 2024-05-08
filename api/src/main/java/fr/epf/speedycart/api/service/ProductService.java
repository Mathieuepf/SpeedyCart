package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProductData(Product product);
    List<Product> getProductsData();
    Optional<Product> getProductData(Long Id);
    Product updateProductData (Product product);
    void deleteProductData(Long id);
}
