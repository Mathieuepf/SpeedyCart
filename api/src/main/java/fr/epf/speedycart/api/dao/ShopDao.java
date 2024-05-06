package fr.epf.speedycart.api.dao;

import fr.epf.speedycart.api.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopDao extends JpaRepository<Shop, Integer> {
    Shop findById(long id);
    List<Shop> findAll();
}
