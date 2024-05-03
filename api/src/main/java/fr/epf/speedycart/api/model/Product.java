package fr.epf.speedycart.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Product {

        @Id 
        private Long productId;
        private String name;
        private Float unitPrice;
        private String description;
        private Long stock;
        private Date activeFrom;
        private Boolean deactivated;
        private Date deactivateFrom;
        private Float weight;
        private Float sizes;
        private Boolean forAdults;
        private Long shopId;
}
