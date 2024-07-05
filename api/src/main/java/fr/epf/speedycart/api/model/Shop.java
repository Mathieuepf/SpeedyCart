package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Za-z ]{1,50}",
            message = "Name must be 1-50 characters and only A-Z or a-z")
    @NotNull(message = "Name can not be null")
    private String name;

    @Length(min = 1, max = 300,
            message = "Description must be 1-300 characters")
    private String description;

    @PastOrPresent(message = "Active Since must be a past or the current date")
    private LocalDateTime activeSince;

    @FutureOrPresent(message = "Disable Since must be a future or the current date")
    private LocalDateTime disableSince;

    @Pattern(regexp = "[0-9]{14}",
            message = "Siret must be 14 characters and 0-9")
    @NotNull(message = "Siret can not be null")
    private String siret;

    @OneToOne
    @JoinColumn(name = "address_id", unique = true)
    @NotNull(message = "Adress can not be null")
    private Address address;
}
