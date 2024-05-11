package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.DeliveryPerson;
import fr.epf.speedycart.api.repository.DeliveryPersonDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryPersonServiceImpl implements DeliveryPersonService {
    @Autowired
    DeliveryPersonDao deliveryPersonDao;

    @Override
    public DeliveryPerson saveDeliveryPersonData(@Valid DeliveryPerson deliveryPerson) {
        deliveryPerson.setId(0L);
        return deliveryPersonDao.save(deliveryPerson);
    }

    @Override
    public void deleteDeliveryPersonData(Long id) {
        // check delivery person exist
        DeliveryPerson deliveryPerson = deliveryPersonDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Delivery Person Invalid Id"));

        if (deliveryPerson.getDisableSince() == null) {
            deliveryPerson.setDisableSince(LocalDateTime.now().plusMinutes(5));
            deliveryPersonDao.save(deliveryPerson);
        }
    }
}
