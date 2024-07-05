package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDao extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long id);
}
