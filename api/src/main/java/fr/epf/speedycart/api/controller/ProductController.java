package fr.epf.speedycart.api.controller;


import fr.epf.speedycart.api.dao.ProductDao;
import fr.epf.speedycart.api.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping
    public List<Product> listeProduits() {
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    public Product afficherUnProduit(@PathVariable long id) {
        return productDao.findById(id);
    }
}
