package fr.epf.speedycart.api.controller;


import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product) {
        Product productAdded = productService.saveProductData(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/products")
    public List<Product> getListProduct() {
        return productService.getProductsData();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductData(id);
    }

    @PutMapping("/product")
    public Product setProduct(@RequestBody @Valid Product product) {
        return productService.updateProductData(product);
    }

    @DeleteMapping("/product/{id}")
    public void delProductById(@PathVariable long id) {
        productService.deleteProductData(id);
    }
}
