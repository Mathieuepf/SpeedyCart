package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.DeliveryException;
import fr.epf.speedycart.api.exception.DeliveryNotFoundException;
import fr.epf.speedycart.api.exception.OrderNotFoundException;
import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.*;
import fr.epf.speedycart.api.repository.DeliveryDao;
import fr.epf.speedycart.api.repository.DeliveryPersonDao;
import fr.epf.speedycart.api.repository.OrderDao;
import fr.epf.speedycart.api.repository.ProductOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryDao deliveryDao;

    @Autowired
    DeliveryPersonDao deliveryPersonDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductOrderDao productOrderDao;

    @Override
    public Delivery setDeliveryPersonData(Long id, DeliveryPerson deliveryPerson) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        DeliveryPerson deliveryPersonfind = deliveryPersonDao.findById(deliveryPerson.getId()).orElseThrow(
                () -> new UserNotFoundException("DeliveryPerson Invalid Id"));

        if (delivery.getDeliveryPerson() == null) {
            System.out.println("4");
            delivery.setDeliveryPerson(deliveryPersonfind);
        } else {
            throw new DeliveryException("A delivery Person is already assign");
        }

        return deliveryDao.save(delivery);
    }

    @Override
    public Delivery setDeliveryPreparedData(Long id) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        delivery.setPrepared(true);
        return deliveryDao.save(delivery);
    }

    @Override
    public Delivery setDeliveryGotData(Long id) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        delivery.setGot(true);
        return deliveryDao.save(delivery);
    }

    @Override
    public Delivery setDeliveryAcceptedData(Long id) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        delivery.setAccepted(true);
        return deliveryDao.save(delivery);
    }

    @Override
    public Delivery setDeliveryDeliveredData(Long id) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        delivery.setDelivered(true);
        delivery.setArriveAt(LocalDateTime.now().plusMinutes(5));
        return deliveryDao.save(delivery);
    }

    @Override
    public Delivery setDeliveryDisableData(Long id) {
        Delivery delivery = deliveryDao.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException("Invalid Id"));
        boolean bool = !delivery.isPrepared() && delivery.getDeliveryPerson() == null;
        if (bool) {
            delivery.setDisable(true);
        }
        return deliveryDao.save(delivery);
    }

    @Override
    public List<OrderDTO> getDeliveryWaitingByDeliveryPersonData(long id) {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setId(id);
        List<Delivery> deliveries = deliveryDao.findDeliveriesByDisableFalseAndDeliveredFalseAndDeliveryPerson(deliveryPerson);
        List<Order> orders = findOrdersFromDeliveries(deliveries);
        return ordersToOrderDTOs(orders);
    }

    private List<Order> findOrdersFromDeliveries(List<Delivery> deliveries) {
        List<Order> orders = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            List<Order> orderList = orderDao.findByDelivery(delivery);
            orders.addAll(orderList);
        }
        return orders;
    }

    private List<OrderDTO> ordersToOrderDTOs(List<Order> orders) {
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No records");
        }

        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            OrderDTO newOrder = findProductsLinkToOrder(order);
            if (newOrder != null) {
                orderDTOS.add(newOrder);
            }
        }
        return orderDTOS;
    }

    private OrderDTO findProductsLinkToOrder(Order order) {
        OrderDTO newOrder = new OrderDTO();
        List<ProductOrder> productOrders = productOrderDao.findByOrder(order);
        if (!productOrders.isEmpty()) {
            List<ProductDTO> products = productOrders.stream()
                    .map(productOrder -> {
                        ProductDTO productDTO = new ProductDTO();
                        productDTO.setProduct(productOrder.getProduct());
                        productDTO.setQuantity(productOrder.getQuantity());
                        return productDTO;
                    })
                    .collect(Collectors.toList());

            newOrder.setOrder(order);
            newOrder.setProducts(products);
            return newOrder;
        }
        return null;
    }
}
