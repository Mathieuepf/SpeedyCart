package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "_User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mail;
    private String password;

    @OneToOne
    @JoinColumn(name = "client_id", unique = true)
    private Client client;

    @OneToOne
    @JoinColumn(name = "shop_id", unique = true)
    private Shop shop;

    @OneToOne
    @JoinColumn(name = "delivery_person_id", unique = true)
    private DeliveryPerson deliveryPerson;

    @OneToOne
    @JoinColumn(name = "admin_id", unique = true)
    private Admin admin;
}
