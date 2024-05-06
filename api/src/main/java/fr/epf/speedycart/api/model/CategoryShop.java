package fr.epf.speedycart.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CategoryShop {
    @Id
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
