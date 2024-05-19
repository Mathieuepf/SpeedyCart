package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.UserException;
import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.DeliveryPerson;
import fr.epf.speedycart.api.repository.DeliveryPersonDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DeliveryPersonServiceTest {

    @InjectMocks
    private DeliveryPersonServiceImpl deliveryPersonService;

    @Mock
    private DeliveryPersonDao deliveryPersonDao;

    @Test
    public void saveDeliveryPersonDataTest() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setId(0L);

        when(deliveryPersonDao.save(deliveryPerson)).thenReturn(deliveryPerson);
        DeliveryPerson saved = deliveryPersonService.saveDeliveryPersonData(deliveryPerson);
        assertEquals(saved, deliveryPerson);
    }

    @Test
    public void deleteDeliveryPersonDataTest() {
        Long id = 0L;
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setId(id);
        deliveryPerson.setDisableSince(null);

        when(deliveryPersonDao.findById(id)).thenReturn(Optional.of(deliveryPerson));
        deliveryPersonService.deleteDeliveryPersonData(id);
        verify(deliveryPersonDao, times(1)).save(deliveryPerson);
        assertEquals(deliveryPerson.getDisableSince().getDayOfYear(), LocalDateTime.now().plusMinutes(5).getDayOfYear());
    }

    @Test
    public void deleteDeliveryPersonDataNotFoundTest() {
        Long id = 0L;

        when(deliveryPersonDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> deliveryPersonService.deleteDeliveryPersonData(id));
    }

    @Test
    public void deleteDeliveryPersonData_AlreadyDisabled() {
        Long deliveryPersonId = 0L;
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setId(deliveryPersonId);
        deliveryPerson.setDisableSince(LocalDateTime.now().minusDays(1));

        when(deliveryPersonDao.findById(deliveryPersonId)).thenReturn(Optional.of(deliveryPerson));
        assertThrows(UserException.class, () -> deliveryPersonService.deleteDeliveryPersonData(deliveryPersonId));
    }
}
