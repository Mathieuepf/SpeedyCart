package fr.epf.speedycart.api.dao;

import fr.epf.speedycart.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    User findById(long id);
    List<User> findAll();
}
