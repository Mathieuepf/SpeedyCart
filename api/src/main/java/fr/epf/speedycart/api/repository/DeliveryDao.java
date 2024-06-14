package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.Delivery;
import fr.epf.speedycart.api.model.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryDao extends JpaRepository<Delivery, Long> {
    List<Delivery> findDeliveriesByDisableFalseAndDeliveryPersonIsNull();

    List<Delivery> findDeliveriesByDisableFalseAndPreparedFalse();

    List<Delivery> findDeliveriesByDisableFalseAndDeliveredFalseAndDeliveryPerson(DeliveryPerson deliveryPerson);
}
