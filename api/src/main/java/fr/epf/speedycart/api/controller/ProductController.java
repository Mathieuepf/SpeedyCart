package fr.epf.speedycart.api.controller;


import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getListProduct() { return productService.getProductsData(); }

    @GetMapping("product/{id}")
    public Optional<Product> getProductById(@PathVariable long id) { return productService.getProductData(id); }

    @DeleteMapping("product/{id}")
    public void delProductById(@PathVariable long id){ productService.deleteProductData(id); }
}
