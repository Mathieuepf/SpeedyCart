package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.ProductNotFoundException;
import fr.epf.speedycart.api.exception.ShopNotFoundException;
import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.repository.ProductDao;
import fr.epf.speedycart.api.repository.ShopDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Autowired
    ProductOrderService productOrderService;

    @Autowired
    ShopDao shopDao;

    @Override
    public Product saveProductData(@Valid Product product) {
        // check if the shop exists
        shopDao.findById(product.getShop().getId())
                .orElseThrow(() -> new ShopNotFoundException("Invalid Id"));

        product.setId(0L);
        return productDao.save(product);
    }

    @Override
    public List<Product> getProductsData() {
        List<Product> products = productDao.findAll();
        if (products.size() == 0) {
            throw new ProductNotFoundException("No records");
        }
        return products;
    }

    @Override
    public Product getProductData(Long Id) {
        return productDao.findById(Id)
                .orElseThrow(() -> new ProductNotFoundException("Invalid Id"));
    }

    @Override
    public Product updateProductData(@Valid Product product) {
        // check if the product exists
        this.getProductData(product.getId());

        // check if the shop exists
        shopDao.findById(product.getShop().getId())
                .orElseThrow(() -> new ShopNotFoundException("Invalid Id"));

        return productDao.save(product);
    }

    @Override
    public void deleteProductData(Long id) {
        // check if the product exists
        Product product = this.getProductData(id);

        // check if the product is linked to any order
        boolean linkedToOrder = productOrderService.existsByProductData(product);
        if (linkedToOrder) {
            if (product.getDisableSince() == null) {
                product.setDisableSince(LocalDateTime.now());
                productDao.save(product);
            }
        } else {
            productDao.delete(product);
        }
    }
}
