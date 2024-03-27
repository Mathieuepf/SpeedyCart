package fr.epf.speedycart.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Product {

        @Id 
        private Long productId;
        private String name;
        private Float unitPrice;
        private String description;
        private Long stock;
        private Date activeFrom;
        private Boolean deactivated;
        private Date deactivateFrom;
        private Float weight;
        private Float sizes;
        private Boolean forAdults;
        private Long shopId;

    public Product() {
    }

    public Product(Long productId,
                   String name,
                   Float unitPrice,
                   String description,
                   Long stock,
                   Date activeFrom,
                   Boolean deactivated,
                   Date deactivateFrom,
                   Float weight,
                   Float sizes,
                   Boolean forAdults,
                   Long shopId) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.stock = stock;
        this.activeFrom = activeFrom;
        this.deactivated = deactivated;
        this.deactivateFrom = deactivateFrom;
        this.weight = weight;
        this.sizes = sizes;
        this.forAdults = forAdults;
        this.shopId = shopId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }

    public Date getDeactivateFrom() {
        return deactivateFrom;
    }

    public void setDeactivateFrom(Date deactivateFrom) {
        this.deactivateFrom = deactivateFrom;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getSizes() {
        return sizes;
    }

    public void setSizes(Float sizes) {
        this.sizes = sizes;
    }

    public Boolean isForAdults() {
        return forAdults;
    }

    public void setForAdults(Boolean forAdults) {
        this.forAdults = forAdults;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
