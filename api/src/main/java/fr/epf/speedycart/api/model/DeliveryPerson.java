package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String vehicle;
    private LocalDateTime dateOfBirth;
    private LocalDateTime activeSince;
    private LocalDateTime disableSince;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
