package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProductData(Product product);

    List<Product> getProductsData();

    Product getProductData(Long Id);

    Product updateProductData(Product product);

    void deleteProductData(Long id);
}
