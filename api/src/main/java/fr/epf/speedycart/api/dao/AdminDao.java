package fr.epf.speedycart.api.dao;

import fr.epf.speedycart.api.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminDao extends JpaRepository<Admin, Integer> {
    Admin findById(long id);
    List<Admin> findAll();
}
