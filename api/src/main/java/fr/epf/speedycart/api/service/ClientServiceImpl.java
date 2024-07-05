package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.UserException;
import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.Client;
import fr.epf.speedycart.api.repository.ClientDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientDao clientDao;

    @Override
    public Client saveClientData(@Valid Client client) {
        client.setId(0L);
        return clientDao.save(client);
    }

    @Override
    public void deleteClientData(Long id) {
        //check if the client exist
        Client client = clientDao.findById(id).orElseThrow(
                () -> new UserNotFoundException("Client Invalid Id"));

        if (client.getDisableSince() != null) {
            throw new UserException("Client is already disabled");
        }

        client.setDisableSince(LocalDateTime.now().plusMinutes(5));
        clientDao.save(client);
    }
}
