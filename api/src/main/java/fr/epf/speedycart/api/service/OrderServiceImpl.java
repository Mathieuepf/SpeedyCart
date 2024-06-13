package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.OrderNotFoundException;
import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.*;
import fr.epf.speedycart.api.repository.ClientDao;
import fr.epf.speedycart.api.repository.DeliveryDao;
import fr.epf.speedycart.api.repository.OrderDao;
import fr.epf.speedycart.api.repository.ProductOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductOrderService productOrderService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductOrderDao productOrderDao;

    @Autowired
    DeliveryDao deliveryDao;

    @Autowired
    ClientDao clientDao;

    @Override
    public Order saveOrderData(OrderDTO orderDTO) {
        // check client exist
        Long clientId = orderDTO.getOrder().getClient().getId();
        clientDao.findById(clientId).orElseThrow(
                () -> new UserNotFoundException("Client Invalid Id"));

        Order order = orderDTO.getOrder();
        order.setId(0L);
        order.setOrderAt(LocalDateTime.now());

        // create new delivery
        Delivery delivery = new Delivery();
        delivery.setFee(calculateFee(orderDTO));
        order.setDelivery(deliveryDao.save(delivery));

        Order newOrder = orderDao.save(order);

        // link Products to Order
        for (ProductDTO productDTO : orderDTO.getProducts()) {
            saveNewProductOrder(productDTO, newOrder);
        }

        return newOrder;
    }

    private double calculateFee(OrderDTO orderDTO) {
        double fee = 0.0;
        for (ProductDTO productDTO : orderDTO.getProducts()) {
            Product product = productService.getProductData(productDTO.getProduct().getId());
            double unitPrice = product.getUnitPrice();
            int quantity = productDTO.getQuantity();
            fee += unitPrice * quantity;
        }
        return fee;
    }

    private void saveNewProductOrder(ProductDTO productDTO, Order order) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrder(order);

        Product product = productService.getProductData(productDTO.getProduct().getId());
        productOrder.setProduct(product);

        productOrder.setQuantity(productDTO.getQuantity());
        System.out.println(productOrder);
        productOrderDao.save(productOrder);
    }

    @Override
    public List<OrderDTO> getOrdersData() {
        List<Order> orders = orderDao.findAll();
        return ordersToOrderDTOs(orders);
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

    @Override
    public OrderDTO getOrderData(long id) {
        Order order = orderDao.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Invalid Id"));

        OrderDTO newOrder = findProductsLinkToOrder(order);
        if (newOrder == null) {
            throw new OrderNotFoundException("Invalid Id");
        }
        return newOrder;
    }

    @Override
    public List<OrderDTO> getOrdersWaitingData() {
        List<Order> orders = getOrdersWaiting();
        return ordersToOrderDTOs(orders);
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

    private List<Order> getOrdersWaiting() {
        List<Delivery> deliveries = deliveryDao.findDeliveriesByDisableFalseAndDeliveryPersonIsNull();

        List<Order> orders = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            Optional<Order> optionalOrder = orderDao.findByDelivery(delivery);
            optionalOrder.ifPresent(orders::add);
        }
        return orders;
    }
}
