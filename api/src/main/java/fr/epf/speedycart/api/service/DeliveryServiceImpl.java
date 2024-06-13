package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.DeliveryException;
import fr.epf.speedycart.api.exception.DeliveryNotFoundException;
import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.Delivery;
import fr.epf.speedycart.api.model.DeliveryPerson;
import fr.epf.speedycart.api.repository.DeliveryDao;
import fr.epf.speedycart.api.repository.DeliveryPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryDao deliveryDao;

    @Autowired
    DeliveryPersonDao deliveryPersonDao;

    @Override
    public Delivery setDeliveryPersonData(Long id, DeliveryPerson deliveryPerson) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        DeliveryPerson deliveryPersonfind = deliveryPersonDao.findById(deliveryPerson.getId()).orElseThrow(
                () -> new UserNotFoundException("DeliveryPerson Invalid Id"));

        if (delivery.getDeliveryPerson() == null) {
            System.out.println("4");
            delivery.setDeliveryPerson(deliveryPersonfind);
        } else {
            throw new DeliveryException("A delivery Person is already assign");
        }

        return deliveryDao.save(delivery);
    }
}
