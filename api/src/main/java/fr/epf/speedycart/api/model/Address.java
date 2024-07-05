package fr.epf.speedycart.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Za-z0-9]{1,10}",
            message = "Number must be 1-10 characters")
    @NotNull(message = "Number can not be null")
    private String number;

    @Pattern(regexp = "[A-Za-z0-9-]{1,50}",
            message = "Road must be 1-50 characters")
    @NotNull(message = "Road can not be null")
    private String road;

    @Pattern(regexp = "[A-Za-z0-9-]{1,50}",
            message = "City must be 1-50 characters")
    @NotNull(message = "City can not be null")
    private String city;

    @Length(min = 1, max = 50,
            message = "AddInfo must be 1-50 characters")
    private String addInfo;
}
