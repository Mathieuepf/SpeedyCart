package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.LoginDTO;
import fr.epf.speedycart.api.model.User;

public interface LoginService {
    User checkLoginData(LoginDTO loginDTO);
}
