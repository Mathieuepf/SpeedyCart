package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.UserException;
import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.User;
import fr.epf.speedycart.api.model.Client;
import fr.epf.speedycart.api.model.DeliveryPerson;
import fr.epf.speedycart.api.model.Admin;
import fr.epf.speedycart.api.model.Shop;
import fr.epf.speedycart.api.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Mock
    private ClientService clientService;

    @Mock
    private DeliveryPersonService deliveryPersonService;

    @Mock
    private AdminService adminService;

    @Mock
    private ShopService shopService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(0L);
        user.setMail("test@example.com");
    }

    @Test
    public void saveUserDataClientTest() {
        Client client = new Client();
        user.setClient(client);

        when(userDao.findByMail(user.getMail())).thenReturn(Optional.empty());
        when(clientService.saveClientData(client)).thenReturn(client);
        when(userDao.save(user)).thenReturn(user);

        User savedUser = userService.saveUserData(user);

        assertNotNull(savedUser);
        assertEquals(user.getMail(), savedUser.getMail());
        verify(clientService, times(1)).saveClientData(client);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void saveUserDataShopTest() {
        Shop shop = new Shop();
        user.setShop(shop);

        when(userDao.findByMail(user.getMail())).thenReturn(Optional.empty());
        when(shopService.saveShopData(shop)).thenReturn(shop);
        when(userDao.save(user)).thenReturn(user);

        User savedUser = userService.saveUserData(user);

        assertNotNull(savedUser);
        assertEquals(user.getMail(), savedUser.getMail());
        verify(shopService, times(1)).saveShopData(shop);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void saveUserDataAdminTest() {
        Admin admin = new Admin();
        user.setAdmin(admin);

        when(userDao.findByMail(user.getMail())).thenReturn(Optional.empty());
        when(adminService.saveAdminData(admin)).thenReturn(admin);
        when(userDao.save(user)).thenReturn(user);

        User savedUser = userService.saveUserData(user);

        assertNotNull(savedUser);
        assertEquals(user.getMail(), savedUser.getMail());
        verify(adminService, times(1)).saveAdminData(admin);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void saveUserDataDeliveryPersonTest() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        user.setDeliveryPerson(deliveryPerson);

        when(userDao.findByMail(user.getMail())).thenReturn(Optional.empty());
        when(deliveryPersonService.saveDeliveryPersonData(deliveryPerson)).thenReturn(deliveryPerson);
        when(userDao.save(user)).thenReturn(user);

        User savedUser = userService.saveUserData(user);

        assertNotNull(savedUser);
        assertEquals(user.getMail(), savedUser.getMail());
        verify(deliveryPersonService, times(1)).saveDeliveryPersonData(deliveryPerson);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void saveUserDataEmailNotUniqueTest() {
        when(userDao.findByMail(user.getMail())).thenReturn(Optional.of(user));

        assertThrows(UserException.class, () -> userService.saveUserData(user));

        verify(userDao, times(1)).findByMail(user.getMail());
        verify(userDao, times(0)).save(user);
    }

    @Test
    public void saveUserDataMultipleUserTypesTest() {
        user.setClient(new Client());
        user.setShop(new Shop());

        assertThrows(UserException.class, () -> userService.saveUserData(user));
    }

    @Test
    public void getUsersDataSuccessTest() {
        User user1 = new User();
        user1.setId(0L);
        User user2 = new User();
        user2.setId(0L);
        List<User> users = Arrays.asList(user1, user2);

        when(userDao.findAll()).thenReturn(users);
        List<User> returnedUsers = userService.getUsersData();
        assertEquals(returnedUsers, users);
    }

    @Test
    public void getUsersDataNotFoundTest() {
        when(userDao.findAll()).thenReturn(List.of());

        assertThrows(UserNotFoundException.class, () -> userService.getUsersData());
    }

    @Test
    public void getUserDataSuccessTest() {
        when(userDao.findById(user.getId())).thenReturn(Optional.of(user));
        User foundUser = userService.getUserData(user.getId());

        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
    public void getUserDataNotFoundTest() {
        when(userDao.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserData(user.getId()));
    }

    @Test
    public void updateUserDataSuccessTest() {
        Shop shop = new Shop();
        user.setShop(shop);

        when(userDao.findById(user.getId())).thenReturn(Optional.of(user));
        when(userDao.save(user)).thenReturn(user);

        User updatedUser = userService.updateUserData(user);

        assertNotNull(updatedUser);
        assertEquals(user.getId(), updatedUser.getId());
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void updateUserDataNotFoundTest() {
        when(userDao.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.updateUserData(user));
    }

    @Test
    public void deleteUserDataClientSuccessTest() {
        Client client = new Client();
        user.setClient(client);
        when(userDao.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUserData(user.getId());

        verify(userDao, times(1)).deleteById(user.getId());
        verify(clientService, times(1)).deleteClientData(client.getId());
    }

    @Test
    public void deleteUserDataShopSuccessTest() {
        Shop shop = new Shop();
        shop.setId(0L);
        user.setShop(shop);
        when(userDao.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUserData(user.getId());

        verify(userDao, times(1)).deleteById(user.getId());
        verify(shopService, times(1)).deleteShopData(shop.getId());
    }

    @Test
    public void deleteUserDataAdminSuccessTest() {
        Admin admin = new Admin();
        admin.setId(0L);
        user.setAdmin(admin);
        when(userDao.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUserData(user.getId());

        verify(userDao, times(1)).deleteById(user.getId());
        verify(adminService, times(1)).deleteAdminData(admin.getId());
    }

    @Test
    public void deleteUserDataDeliveryPersonSuccessTest() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setId(0L);
        user.setDeliveryPerson(deliveryPerson);
        when(userDao.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUserData(user.getId());

        verify(userDao, times(1)).deleteById(user.getId());
        verify(deliveryPersonService, times(1)).deleteDeliveryPersonData(deliveryPerson.getId());
    }

    @Test
    public void deleteUserDataNotFoundTest() {
        when(userDao.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteUserData(user.getId()));
    }
}
