package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private double unitPrice;
        private String description;
        private int stock;
        private LocalDateTime activeSince;
        private LocalDateTime disableSince;
        private double weight;
        private double sizes;
        private int forAdults;

        @ManyToOne
        @JoinColumn(name = "shop_id")
        private Shop shop;
}
