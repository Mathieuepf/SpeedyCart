package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrdersData();
}
