package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDao extends JpaRepository<Delivery, Long> {
}
