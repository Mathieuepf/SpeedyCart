package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopDao extends JpaRepository<Shop, Long> {
    Optional<Shop> findById(Long id);
}
