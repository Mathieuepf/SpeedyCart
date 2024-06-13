package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Order;
import fr.epf.speedycart.api.model.OrderDTO;
import fr.epf.speedycart.api.model.Product;

import java.util.List;

public interface OrderService {
    Order saveOrderData(OrderDTO orderDTO);

    List<OrderDTO> getOrdersData();

    OrderDTO getOrderData(long id);
}
