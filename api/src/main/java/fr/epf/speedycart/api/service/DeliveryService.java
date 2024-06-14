package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Delivery;
import fr.epf.speedycart.api.model.DeliveryPerson;
import fr.epf.speedycart.api.model.OrderDTO;

import java.util.List;

public interface DeliveryService {
    Delivery setDeliveryPersonData(Long id, DeliveryPerson deliveryPerson);

    Delivery setDeliveryPreparedData(Long id);

    Delivery setDeliveryGotData(Long id);

    Delivery setDeliveryAcceptedData(Long id);

    Delivery setDeliveryDeliveredData(Long id);

    Delivery setDeliveryDisableData(Long id);

    List<OrderDTO> getDeliveryWaitingByDeliveryPersonData(long id);
}
