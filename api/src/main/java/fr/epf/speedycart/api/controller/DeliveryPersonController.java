package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.dao.DeliveryPersonDao;
import fr.epf.speedycart.api.model.DeliveryPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deliverypersons")
public class DeliveryPersonController {

    @Autowired
    private DeliveryPersonDao deliveryPersonDao;

    @GetMapping
    public List<DeliveryPerson> listeDeliveryPerson() {
        return deliveryPersonDao.findAll();
    }

    @GetMapping("/{id}")
    public DeliveryPerson afficherUnDeliveryPerson(@PathVariable long id) {
        return deliveryPersonDao.findById(id);
    }
}
