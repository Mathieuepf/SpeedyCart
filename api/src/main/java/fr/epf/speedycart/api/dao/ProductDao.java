package fr.epf.speedycart.api.dao;

import fr.epf.speedycart.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    Product findById(int id);
    List<Product> findAll();
}
