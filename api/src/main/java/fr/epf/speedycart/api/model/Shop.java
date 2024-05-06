package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime activeSince;
    private LocalDateTime disableSince;
    private String siret;

    @OneToOne
    @JoinColumn(name = "address_id", unique = true)
    private Address address;
}
