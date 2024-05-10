package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.repository.ProductOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    @Autowired
    ProductOrderDao productOrderDao;

    @Override
    public boolean existsByProductData(Product product) {
        return productOrderDao.existsByProduct(product);
    }
}
