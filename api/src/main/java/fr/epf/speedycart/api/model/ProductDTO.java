package fr.epf.speedycart.api.model;

import lombok.Data;

@Data
public class ProductDTO {
    private Product product;
    private int quantity;
}
