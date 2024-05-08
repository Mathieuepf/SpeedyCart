package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.ProductOrder;
import fr.epf.speedycart.api.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderDao extends JpaRepository<ProductOrder, Long> {
    // TODO : remove query when Hibernate decide to work
    List<ProductOrder> findByProductId(@Param("productId") Long productId);
}
