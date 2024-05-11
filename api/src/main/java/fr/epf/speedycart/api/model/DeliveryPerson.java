package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Data
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Za-z-]{1,50}",
            message = "First Name must be 1-50 characters and only A-Z or a-z")
    @NotNull(message = "First Name can not be null")
    private String firstname;

    @Pattern(regexp = "[A-Za-z-]{1,50}",
            message = "Last Name must be 1-50 characters and only A-Z or a-z")
    @NotNull(message = "Last Name can not be null")
    private String lastname;

    @Length(min = 3, max = 50,
            message = "Vehicle must be 3-50 characters")
    @NotNull(message = "Vehicle can not be null")
    private String vehicle;

    @Past(message = "DateOfBirth must be in the past")
    @NotNull(message = "Date of Birth can not be null")
    private LocalDateTime dateOfBirth;

    @PastOrPresent(message = "Active Since must be a past or the current date")
    private LocalDateTime activeSince;

    @FutureOrPresent(message = "Disable Since must be a future or the current date")
    private LocalDateTime disableSince;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
