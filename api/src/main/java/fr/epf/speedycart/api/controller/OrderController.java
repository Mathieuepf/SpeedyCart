package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.model.OrderDTO;
import fr.epf.speedycart.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDTO> getListOrders() {
        return orderService.getOrdersData();
    }
}
