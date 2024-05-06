package fr.epf.speedycart.api.controller;

import fr.epf.speedycart.api.dao.ClientDao;
import fr.epf.speedycart.api.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientDao clientDao;

    @GetMapping
    public List<Client> listClients() {
        return clientDao.findAll();
    }

    @GetMapping("/{id}")
    public Client afficherUnClient(@PathVariable long id) {
        return clientDao.findById(id);
    }
}
