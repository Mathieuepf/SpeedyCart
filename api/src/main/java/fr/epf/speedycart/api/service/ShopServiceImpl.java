package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.AddressNotFoundException;
import fr.epf.speedycart.api.exception.ProductNotFoundException;
import fr.epf.speedycart.api.exception.ShopNotFoundException;
import fr.epf.speedycart.api.exception.UserException;
import fr.epf.speedycart.api.model.Address;
import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.model.Shop;
import fr.epf.speedycart.api.repository.ProductDao;
import fr.epf.speedycart.api.repository.ShopDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopDao shopDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    AddressService addressService;

    @Override
    public Shop saveShopData(@Valid Shop shop) {
        //create Address if it doesn't exist
        try {
            addressService.getAddressData(shop.getAddress().getId());
        } catch (AddressNotFoundException e) {
            Address address = addressService.saveAddressData(shop.getAddress());
            shop.setAddress(address);
        }

        shop.setId(0L);
        return shopDao.save(shop);
    }

    @Override
    public Shop setShopData(@Valid Shop shop) {
        this.getShopData(shop.getId());

        // check if address exists
        addressService.getAddressData(shop.getAddress().getId());

        return shopDao.save(shop);
    }

    @Override
    public List<Shop> getShopsData() {
        List<Shop> shops = shopDao.findAll();
        if (shops.isEmpty()) {
            throw new ShopNotFoundException("No records");
        }
        return shops;
    }

    @Override
    public Shop getShopData(Long Id) {
        return shopDao.findById(Id)
                .orElseThrow(() -> new ShopNotFoundException("Invalid Id"));
    }

    @Override
    public List<Product> getProductsFromShopData(Long Id) {
        // check if the shop exists
        this.getShopData(Id);
        List<Product> products = productDao.findByShopId(Id);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No records");
        }
        return products;
    }

    @Override
    public void deleteShopData(Long Id) {
        //check if the shop exists
        Shop shop = this.getShopData(Id);

        if (shop.getDisableSince() != null) {
            throw new UserException("Client is already disabled");
        }

        shop.setDisableSince(LocalDateTime.now().plusMinutes(5));
        shopDao.save(shop);
    }
}
