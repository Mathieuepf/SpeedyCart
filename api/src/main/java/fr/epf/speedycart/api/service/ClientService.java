package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Client;

public interface ClientService {
    Client saveClientData(Client client);

    void deleteClientData(Long id);
}
