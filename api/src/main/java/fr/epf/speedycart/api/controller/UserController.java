package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.model.User;
import fr.epf.speedycart.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*
    TODO : gerer les exeptions
    @PostMapping("/user")
    public User saveUser(@RequestBody User user){ return userService.saveUserData(user); }
    */

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsersData();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable long id) {
        return userService.getUserData(id);
    }

    @PutMapping("/user")
    public User setUser(@RequestBody User user){ return userService.updateUserData(user);}

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id){ userService.deleteUserData(id); }
}
