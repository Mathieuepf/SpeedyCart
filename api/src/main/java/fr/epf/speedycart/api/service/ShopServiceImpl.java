package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.ShopNotFoundException;
import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.model.Shop;
import fr.epf.speedycart.api.repository.ProductDao;
import fr.epf.speedycart.api.repository.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    ShopDao shopDao;

    @Autowired
    ProductDao productDao;

    @Override
    public List<Shop> getShopsData() {
        return shopDao.findAll();
    }

    @Override
    public Shop getShopData(Long Id) {
        return shopDao.findById(Id)
                .orElseThrow(()->new ShopNotFoundException("Invalid Id"));
    }

    @Override
    public List<Product> getProductsFromShopData(Long Id) {
        return productDao.findByShopId(Id);
    }
}
