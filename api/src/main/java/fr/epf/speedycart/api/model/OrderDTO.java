package fr.epf.speedycart.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    @NotNull(message = "Order can not be null")
    private Order order;

    @NotNull(message = "Order products can not be null")
    private List<ProductDTO> products;
}
