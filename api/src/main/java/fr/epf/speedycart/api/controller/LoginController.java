package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.model.LoginDTO;
import fr.epf.speedycart.api.model.User;
import fr.epf.speedycart.api.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public User getUserByLogin(@RequestBody @Valid LoginDTO loginDTO) {
        return loginService.checkLoginData(loginDTO);
    }
}
