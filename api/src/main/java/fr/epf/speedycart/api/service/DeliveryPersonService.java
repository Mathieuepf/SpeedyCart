package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.DeliveryPerson;

public interface DeliveryPersonService {
    DeliveryPerson saveDeliveryPersonData(DeliveryPerson deliveryPerson);

    void deleteDeliveryPersonData(Long id);
}
