package fr.epf.speedycart.api.dao;

import fr.epf.speedycart.api.model.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryPersonDao extends JpaRepository<DeliveryPerson, Integer> {
    DeliveryPerson findById(long id);
    List<DeliveryPerson> findAll();
}
