package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.OrderNotFoundException;
import fr.epf.speedycart.api.model.Order;
import fr.epf.speedycart.api.model.OrderDTO;
import fr.epf.speedycart.api.model.Product;
import fr.epf.speedycart.api.model.ProductOrder;
import fr.epf.speedycart.api.repository.OrderDao;
import fr.epf.speedycart.api.repository.ProductOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductOrderService productOrderService;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductOrderDao productOrderDao;

    @Override
    public List<OrderDTO> getOrdersData() {
        List<Order> orders = orderDao.findAll();
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No records");
        }

        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            List<ProductOrder> productOrders = productOrderDao.findByOrder(order);
            if (!productOrders.isEmpty()) {

                List<Product> products = productOrders.stream()
                        .map(ProductOrder::getProduct)
                        .collect(Collectors.toList());

                OrderDTO newOrder = new OrderDTO();
                newOrder.setOrder(order);
                newOrder.setProducts(products);
                orderDTOS.add(newOrder);
            }
        }
        return orderDTOS;
    }
}
