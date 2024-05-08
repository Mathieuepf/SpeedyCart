package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.ProductNotFoundException;
import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.repository.ProductDao;
import fr.epf.speedycart.api.repository.ProductOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDao ProductRepository;

    @Autowired
    ProductOrderDao productOrderDao;

    //TODO : programmer lors du sprint 2
    @Override
    public Product saveProductData(Product product) {
        return ProductRepository.save(product);
    }

    @Override
    public List<Product> getProductsData() {
        return ProductRepository.findAll();
    }

    @Override
    public Optional<Product> getProductData(Long Id) {
        Optional<Product> product =ProductRepository.findById(Id);
        if (product.isEmpty()){
            throw new ProductNotFoundException("Invalid Id");
        }
        return product;
    }

    //TODO : programmer lors du sprint 2
    @Override
    public Product updateProductData(Product product) {
        return ProductRepository.save(product);
    }

    @Override
    public void deleteProductData(Long id) {
        /*
        // cherche si le produit est lier a des commandes
        List<ProductOrder> orders = productOrderDao.findByProductId(id);

        if (!orders.isEmpty()) {
            // modifit le statue du produit en non disponible
        } else {
            // Supprimer le produit
            roductDao.deleteById(id);
        }
        */
    }

}
