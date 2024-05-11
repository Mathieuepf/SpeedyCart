package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.Admin;
import fr.epf.speedycart.api.repository.AdminDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public Admin saveAdminData(@Valid Admin admin) {
        admin.setId(0L);
        return adminDao.save(admin);
    }

    @Override
    public void deleteAdminData(Long id) {
        // check admin exist
        adminDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Admin invalid Id"));

        adminDao.deleteById(id);
    }
}
