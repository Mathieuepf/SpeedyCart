package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.UserException;
import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.LoginDTO;
import fr.epf.speedycart.api.model.User;
import fr.epf.speedycart.api.repository.UserDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserDao userDao;

    @Override
    public User checkLoginData(@Valid LoginDTO loginDTO) {
        User existingUser = userDao.findByMail(loginDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("No user with this email"));

        // check correct password
        if (!Objects.equals(loginDTO.getPassword(), existingUser.getPassword())) {
            throw new UserException("Incorrect password");
        }

        return existingUser;
    }
}
