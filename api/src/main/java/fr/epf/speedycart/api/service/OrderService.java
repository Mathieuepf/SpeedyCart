package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Order;
import fr.epf.speedycart.api.model.OrderDTO;

import java.util.List;

public interface OrderService {
    Order saveOrderData(OrderDTO orderDTO);

    List<OrderDTO> getOrdersData();

    OrderDTO getOrderData(long id);

    List<OrderDTO> getOrdersWaitingData();

    List<OrderDTO> getOrdersWaitingShopData(long id);
}
