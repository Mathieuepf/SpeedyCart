package fr.epf.speedycart.api.repository;

import fr.epf.speedycart.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findById(long id);

    Optional<User> findByMail(String str);
}
