package fr.epf.speedycart.api.dao;

import fr.epf.speedycart.api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientDao extends JpaRepository<Client, Integer> {
    Client findById(long id);
    List<Client> findAll();
}
