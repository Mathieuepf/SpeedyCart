package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.model.Delivery;
import fr.epf.speedycart.api.model.DeliveryPerson;
import fr.epf.speedycart.api.model.OrderDTO;
import fr.epf.speedycart.api.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @PatchMapping("/delivery/{deliveryId}/deliveryPerson")
    public Delivery updateDeliveryPerson(@PathVariable Long deliveryId, @RequestBody DeliveryPerson deliveryPerson) {
        return deliveryService.setDeliveryPersonData(deliveryId, deliveryPerson);
    }

    @PatchMapping("/delivery/{deliveryId}/prepared")
    public Delivery updateDeliveryPrepared(@PathVariable Long deliveryId) {
        return deliveryService.setDeliveryPreparedData(deliveryId);
    }

    @PatchMapping("/delivery/{deliveryId}/got")
    public Delivery updateDeliveryGot(@PathVariable Long deliveryId) {
        return deliveryService.setDeliveryGotData(deliveryId);
    }

    @PatchMapping("/delivery/{deliveryId}/accepted")
    public Delivery updateDeliveryAccepted(@PathVariable Long deliveryId) {
        return deliveryService.setDeliveryAcceptedData(deliveryId);
    }

    @PatchMapping("/delivery/{deliveryId}/delivered")
    public Delivery updateDeliveryDelivered(@PathVariable Long deliveryId) {
        return deliveryService.setDeliveryDeliveredData(deliveryId);
    }

    @PatchMapping("/delivery/{deliveryId}/disable")
    public Delivery updateDeliveryDisable(@PathVariable Long deliveryId) {
        return deliveryService.setDeliveryDisableData(deliveryId);
    }

    @GetMapping("/delivery/waiting/deliveryperson/{id}")
    public List<OrderDTO> getListOrdersWaiting(@PathVariable long id) {
        return deliveryService.getDeliveryWaitingByDeliveryPersonData(id);
    }
}
