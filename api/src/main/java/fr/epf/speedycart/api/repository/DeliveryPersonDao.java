package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryPersonDao extends JpaRepository<DeliveryPerson, Long> {
    Optional<DeliveryPerson> findById(DeliveryPerson deliveryPerson);
}
