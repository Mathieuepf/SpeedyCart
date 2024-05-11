package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Entity
@Table(name = "_User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3, max = 30, message = "Email must be 3-30 characters")
    @NotNull(message = "Email can not be null")
    @Email(message = "Invalid email")
    @Column(unique = true)
    private String mail;

    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}",
            message = "Password must be 8-15 characters")
    @NotNull(message = "Password can not be null")
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
