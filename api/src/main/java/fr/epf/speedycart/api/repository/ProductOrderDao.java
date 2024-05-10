package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderDao extends JpaRepository<ProductOrder, Long> {
    boolean existsByProduct(Product product);
}
