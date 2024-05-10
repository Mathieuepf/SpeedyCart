package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(ProductOrderId.class)
public class ProductOrder {
    private int quantity;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}
