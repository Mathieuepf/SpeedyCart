package fr.epf.speedycart.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epf.speedycart.api.model.Admin;
import fr.epf.speedycart.api.model.User;
import fr.epf.speedycart.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveUserTest() throws Exception {
        User user = new User();
        user.setMail("updated@example.com");
        user.setPassword("secret123");
        Admin admin = new Admin();
        admin.setDescription("description");
        user.setAdmin(admin);

        when(userService.saveUserData(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getUsersTest() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setMail("user1@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setMail("user2@example.com");

        when(userService.getUsersData()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserTest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setMail("user@example.com");

        when(userService.getUserData(1L)).thenReturn(user);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void setUserTest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setMail("updated@example.com");
        user.setPassword("secret123");
        Admin admin = new Admin();
        admin.setDescription("description");
        user.setAdmin(admin);

        when(userService.updateUserData(any(User.class))).thenReturn(user);

        mockMvc.perform(put("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserTest() throws Exception {
        doNothing().when(userService).deleteUserData(1L);

        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUserData(1L);
    }
}
