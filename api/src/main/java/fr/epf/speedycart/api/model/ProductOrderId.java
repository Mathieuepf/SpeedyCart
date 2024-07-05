package fr.epf.speedycart.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductOrderId implements Serializable {
    private Long order;
    private Long product;
}
