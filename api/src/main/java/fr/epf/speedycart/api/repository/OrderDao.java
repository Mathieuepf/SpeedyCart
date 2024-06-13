package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.Delivery;
import fr.epf.speedycart.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    Optional<Order> findByDelivery(Delivery delivery);
}
