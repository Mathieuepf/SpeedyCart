package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Delivery;
import fr.epf.speedycart.api.model.DeliveryPerson;

public interface DeliveryService {
    Delivery setDeliveryPersonData(Long id, DeliveryPerson deliveryPerson);

    Delivery setDeliveryPreparedData(Long id);

    Delivery setDeliveryGotData(Long id);

    Delivery setDeliveryAcceptedData(Long id);

    Delivery setDeliveryDeliveredData(Long id);

    Delivery setDeliveryDisableData(Long id);
}
