package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.dao.UserDao;
import fr.epf.speedycart.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public List<User> listeUsers() {
        return userDao.findAll();
    }

    @GetMapping("/{id}")
    public User afficherUnUser(@PathVariable long id) {
        return userDao.findById(id);
    }
}
