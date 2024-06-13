package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.model.Order;
import fr.epf.speedycart.api.model.OrderDTO;
import fr.epf.speedycart.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDTO> getListOrders() {
        return orderService.getOrdersData();
    }

    @GetMapping("/orders/waiting")
    public List<OrderDTO> getListOrdersWaiting() {
        return orderService.getOrdersWaitingData();
    }

    @GetMapping("/order/{id}")
    public OrderDTO getOrderById(@PathVariable long id) {
        return orderService.getOrderData(id);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> saveProduct(@RequestBody OrderDTO orderDTO) {
        Order orderAdded = orderService.saveOrderData(orderDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
