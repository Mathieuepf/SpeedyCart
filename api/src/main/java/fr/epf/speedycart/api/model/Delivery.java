package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Fee can not be null")
    private double fee;

    @FutureOrPresent(message = "Arrive At must be a future or the current date")
    private LocalDateTime arriveAt;

    private boolean got;
    private boolean prepared;
    private boolean accepted;
    private boolean delivered;
    private boolean disable;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private DeliveryPerson deliveryPerson;
}
