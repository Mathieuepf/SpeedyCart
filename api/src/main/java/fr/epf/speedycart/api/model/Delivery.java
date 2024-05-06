package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double fee;
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
