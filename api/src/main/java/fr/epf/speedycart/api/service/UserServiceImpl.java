package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.UserException;
import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.*;
import fr.epf.speedycart.api.repository.UserDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    ClientService clientService;

    @Autowired
    DeliveryPersonService deliveryPersonService;

    @Autowired
    AdminService adminService;

    @Autowired
    ShopService shopService;

    enum UserType {
        CLIENT, SHOP, ADMIN, DELIVERYPERSON
    }

    private UserType findUserTypeData(User user) {
        UserType usertype = UserType.CLIENT;
        int countNonNull = 0;
        if (user.getClient() != null) {
            countNonNull++;
        }
        if (user.getShop() != null) {
            countNonNull++;
            usertype = UserType.SHOP;
        }
        if (user.getAdmin() != null) {
            countNonNull++;
            usertype = UserType.ADMIN;
        }
        if (user.getDeliveryPerson() != null) {
            countNonNull++;
            usertype = UserType.DELIVERYPERSON;
        }

        if (countNonNull == 0) {
            throw new UserException("At least one userType must be non-null");
        } else if (countNonNull != 1) {
            throw new UserException("Exactly one userType must be non-null");
        }

        return usertype;
    }

    private boolean checkEmailIsUnique(User user) {
        String email = user.getMail();
        Optional<User> existingUser = userDao.findByMail(email);

        if (existingUser.isPresent() &&
                !Objects.equals(user.getId(), existingUser.get().getId())) {
            throw new UserException("Email must be unique");
        }
        return true;
    }

    @Override
    public User saveUserData(@Valid User user) {
        // check if email is unique
        user.setId(0L);
        this.checkEmailIsUnique(user);

        // check only one type of userType is not null
        UserType userType = findUserTypeData(user);

        // create an account for the userType and add it to user
        switch (userType) {
            case CLIENT:
                Client savedClient = clientService.saveClientData(user.getClient());
                user.setClient(savedClient);
                break;
            case SHOP:
                Shop savedShop = shopService.saveShopData(user.getShop());
                user.setShop(savedShop);
                break;
            case ADMIN:
                Admin savedAdmin = adminService.saveAdminData(user.getAdmin());
                user.setAdmin(savedAdmin);
                break;
            case DELIVERYPERSON:
                DeliveryPerson savedDeliveryPerson = deliveryPersonService
                        .saveDeliveryPersonData(user.getDeliveryPerson());
                user.setDeliveryPerson(savedDeliveryPerson);
                break;
        }

        // create user
        return userDao.save(user);
    }

    @Override
    public List<User> getUsersData() {
        List<User> users = userDao.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No records");
        }
        return users;
    }

    @Override
    public User getUserData(Long Id) {
        return userDao.findById(Id)
                .orElseThrow(() -> new UserNotFoundException("Invalid Id"));
    }

    @Override
    public User updateUserData(@Valid User user) {
        //check user exits
        User existingUser = this.getUserData(user.getId());

        this.checkEmailIsUnique(user);

        // check user type
        if (this.findUserTypeData(existingUser) != this.findUserTypeData(user)) {
            throw new UserException("User type can not be change");
        }

        return userDao.save(user);
    }

    @Override
    public void deleteUserData(Long id) {
        // check if user exist
        User user = this.getUserData(id);

        // delete user
        userDao.deleteById(id);

        // find userType not null
        UserType userType = findUserTypeData(user);

        // delete account of userType
        switch (userType) {
            case CLIENT -> clientService.deleteClientData(user.getClient().getId());
            case SHOP -> shopService.deleteShopData(user.getShop().getId());
            case ADMIN -> adminService.deleteAdminData(user.getAdmin().getId());
            case DELIVERYPERSON -> deliveryPersonService.deleteDeliveryPersonData(user.getDeliveryPerson().getId());
        }
    }
}
