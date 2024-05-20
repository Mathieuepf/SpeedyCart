package fr.epf.speedycart.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Za-z ]{1,20}",
            message = "First Name must be 1-20 characters and only A-Z or a-z")
    @NotNull(message = "Name can not be null")
    private String name;

    @NotNull(message = "Unit Price can not be null")
    private double unitPrice;

    @Length(min = 1, max = 200,
            message = "Description must be 1-200 characters")
    private String description;

    @Min(value = 0, message = "Stock must be a positive number")
    @NotNull(message = "Stock can not be null")
    private int stock;

    @PastOrPresent(message = "Active Since must be a past or the current date")
    private LocalDateTime activeSince;

    @FutureOrPresent(message = "Disable Since must be a future or the current date")
    private LocalDateTime disableSince;

    @Min(value = 0, message = "Weight must be a positive number")
    @NotNull(message = "Weight can not be null")
    private double weight;

    @Min(value = 0, message = "Sizes must be a positive number")
    @NotNull(message = "Sizes can not be null")
    private double sizes;

    @NotNull(message = "For Adult can not be null")
    private boolean forAdults;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @NotNull(message = "Shop can not be null")
    private Shop shop;
}
