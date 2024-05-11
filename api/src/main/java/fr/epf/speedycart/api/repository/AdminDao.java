package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(Admin admin);
}
