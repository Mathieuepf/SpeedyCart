package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Product;

public interface ProductOrderService {
    boolean existsByProductData(Product product);
}
