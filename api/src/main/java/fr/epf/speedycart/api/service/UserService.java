package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUserData(User user);
    List<User> getUsersData();
    Optional<User> getUserData(Long Id);
    User updateUserData (User user);
    void deleteUserData(Long id);
}
