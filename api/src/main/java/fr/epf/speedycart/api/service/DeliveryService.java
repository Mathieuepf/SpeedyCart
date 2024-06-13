package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Delivery;
import fr.epf.speedycart.api.model.DeliveryPerson;

public interface DeliveryService {
    Delivery setDeliveryPersonData(Long id, DeliveryPerson deliveryPerson);
}
