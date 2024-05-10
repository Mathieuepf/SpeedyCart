package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.User;
import fr.epf.speedycart.api.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public User saveUserData(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> getUsersData() {
        return userDao.findAll();
    }

    @Override
    public User getUserData(Long Id) {
        return userDao.findById(Id).orElseThrow(()-> new RuntimeException());
    }

    @Override
    public User updateUserData(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUserData(Long id) {
        userDao.deleteById(id);
    }
}