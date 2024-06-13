package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.model.Delivery;
import fr.epf.speedycart.api.model.DeliveryPerson;
import fr.epf.speedycart.api.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @PatchMapping("/delivery/{deliveryId}/deliveryPerson")
    public Delivery updateDeliveryPerson(@PathVariable Long deliveryId, @RequestBody DeliveryPerson deliveryPerson) {
        return deliveryService.setDeliveryPersonData(deliveryId, deliveryPerson);
    }
}
