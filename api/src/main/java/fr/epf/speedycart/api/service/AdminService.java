package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Admin;

public interface AdminService {
    Admin saveAdminData(Admin admin);

    void deleteAdminData(Long id);
}
