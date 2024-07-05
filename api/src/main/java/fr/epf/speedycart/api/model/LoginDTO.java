package fr.epf.speedycart.api.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginDTO {
    @Length(min = 3, max = 30, message = "Email must be 3-30 characters")
    @NotNull(message = "Email can not be null")
    @Email(message = "Invalid email")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}",
            message = "Password must be 8-15 characters")
    @NotNull(message = "Password can not be null")
    private String password;
}
