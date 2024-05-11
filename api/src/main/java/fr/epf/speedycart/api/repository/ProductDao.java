package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    Product findById(long id);

    List<Product> findByShopId(Long id);
}
