package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "_Order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent(message = "Order At must be a past or the current date")
    private LocalDateTime orderAt;

    private boolean payed;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull(message = "Client can not be null")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "ship_to")
    private Address shipTo;

    @ManyToOne
    @JoinColumn(name = "charge_to")
    private Address chargeTo;
}
