package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressDao extends JpaRepository<Address, Long> {
    Optional<Address> findById(Long id);
}
