package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.UserException;
import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.Client;
import fr.epf.speedycart.api.repository.ClientDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientDao clientDao;

    @Test
    public void saveClientDataTest() {
        Client client = new Client();
        client.setId(0L);

        when(clientDao.save(client)).thenReturn(client);
        Client saved = clientService.saveClientData(client);
        assertEquals(saved, client);
    }

    @Test
    public void deleteClientDataTest() {
        Long id = 0L;
        Client client = new Client();
        client.setId(id);
        client.setDisableSince(null);

        when(clientDao.findById(id)).thenReturn(Optional.of(client));
        clientService.deleteClientData(id);
        verify(clientDao, times(1)).save(client);
        assertEquals(client.getDisableSince().getDayOfYear(), LocalDateTime.now().plusMinutes(5).getDayOfYear());
    }

    @Test
    public void deleteClientDataNotFoundTest() {
        Long id = 0L;

        when(clientDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> clientService.deleteClientData(id));
    }

    @Test
    public void deleteClientDataAlreadyDisabledTest() {
        Long Id = 0L;
        Client client = new Client();
        client.setId(Id);
        client.setDisableSince(LocalDateTime.now().minusDays(1));

        when(clientDao.findById(Id)).thenReturn(Optional.of(client));
        assertThrows(UserException.class, () -> clientService.deleteClientData(Id));
    }
}
