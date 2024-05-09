package fr.epf.speedycart.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Za-z]{1,50}",
            message = "First Name must be 1-50 characters and only A-Z or a-z")
    @NotNull(message = "First Name can not be null")
    private String firstname;

    @Pattern(regexp = "[A-Za-z]{1,50}",
            message = "Last Name must be 1-50 characters and only A-Z or a-z")
    @NotNull(message = "Last Name can not be null")
    private String lastname;

    @PastOrPresent(message = "Active Since must be a past or the current date")
    private LocalDateTime activeSince;

    @FutureOrPresent(message = "Disable Since must be a future or the current date")
    private LocalDateTime disableSince;

    @Past(message = "DateOfBirth must be in the past")
    @NotNull(message = "Date of Birth can not be null")
    private LocalDateTime dateOfBirth;
}
