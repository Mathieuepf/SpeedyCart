package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.dao.AdminDao;
import fr.epf.speedycart.api.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    @GetMapping
    public List<Admin> listeAdmins() {
        return adminDao.findAll();
    }

    @GetMapping("/{id}")
    public Admin afficherUnAdmin(@PathVariable long id) {
        return adminDao.findById(id);
    }
}
