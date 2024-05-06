package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.dao.ShopDao;
import fr.epf.speedycart.api.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopDao shopDao;

    @GetMapping
    public List<Shop> listeShops() {
        return shopDao.findAll();
    }

    @GetMapping("/{id}")
    public Shop afficherUnShop(@PathVariable long id) {
        return shopDao.findById(id);
    }
}
