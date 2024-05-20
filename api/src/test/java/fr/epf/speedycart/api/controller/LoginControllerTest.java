package fr.epf.speedycart.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epf.speedycart.api.model.LoginDTO;
import fr.epf.speedycart.api.model.User;
import fr.epf.speedycart.api.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getUserByLoginTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("login@exemple.com");
        loginDTO.setPassword("secret123");
        User user = new User();

        when(loginService.checkLoginData(loginDTO)).thenReturn(user);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserByLoginInvalidTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("Invalid email");
        loginDTO.setPassword("secret123");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isBadRequest());
    }
}
