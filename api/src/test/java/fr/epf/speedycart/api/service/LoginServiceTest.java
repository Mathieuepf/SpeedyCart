package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.exception.UserException;
import fr.epf.speedycart.api.model.LoginDTO;
import fr.epf.speedycart.api.model.User;
import fr.epf.speedycart.api.repository.UserDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    private UserDao userDao;

    @Test
    public void checkLoginDataTest() {
        String email = "test@example.com";
        String password = "password";
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(email);
        loginDTO.setPassword(password);

        User user = new User();
        user.setMail(email);
        user.setPassword(password);

        when(userDao.findByMail(email)).thenReturn(Optional.of(user));
        User loggedInUser = loginService.checkLoginData(loginDTO);
        assertEquals(loggedInUser, user);
    }

    @Test
    public void checkLoginDataNotFoundTest() {
        String email = "test@example.com";
        String password = "password";
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(email);
        loginDTO.setPassword(password);

        when(userDao.findByMail(email)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> loginService.checkLoginData(loginDTO));
    }

    @Test
    public void checkLoginDataIncorrectPasswordTest() {
        String email = "test@example.com";
        String password = "password";
        String incorrectPassword = "incorrectPassword";
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(email);
        loginDTO.setPassword(incorrectPassword);

        User user = new User();
        user.setMail(email);
        user.setPassword(password);

        when(userDao.findByMail(email)).thenReturn(Optional.of(user));
        assertThrows(UserException.class, () -> loginService.checkLoginData(loginDTO));
    }
}
