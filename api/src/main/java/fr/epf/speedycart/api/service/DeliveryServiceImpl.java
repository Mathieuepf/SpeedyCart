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

import java.time.LocalDateTime;

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

    @Override
    public Delivery setDeliveryPreparedData(Long id) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        delivery.setPrepared(true);
        return deliveryDao.save(delivery);
    }

    @Override
    public Delivery setDeliveryGotData(Long id) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        delivery.setGot(true);
        return deliveryDao.save(delivery);
    }

    @Override
    public Delivery setDeliveryAcceptedData(Long id) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        delivery.setAccepted(true);
        return deliveryDao.save(delivery);
    }

    @Override
    public Delivery setDeliveryDeliveredData(Long id) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        delivery.setDelivered(true);
        delivery.setArriveAt(LocalDateTime.now().plusMinutes(5));
        return deliveryDao.save(delivery);
    }

    @Override
    public Delivery setDeliveryDisableData(Long id) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        boolean bool = !delivery.isPrepared() && delivery.getDeliveryPerson() == null;
        if (bool) {
            delivery.setDisable(true);
        }
        return deliveryDao.save(delivery);
    }
}
