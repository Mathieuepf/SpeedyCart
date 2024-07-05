package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.model.User;
import fr.epf.speedycart.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        User userAdded = userService.saveUserData(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsersData();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUserData(id);
    }

    @PutMapping("/user")
    public User setUser(@Valid @RequestBody User user) {
        return userService.updateUserData(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserData(id);
    }
}
