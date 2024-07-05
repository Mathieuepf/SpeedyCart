package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.AddressNotFoundException;
import fr.epf.speedycart.api.model.Address;
import fr.epf.speedycart.api.repository.AddressDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressDao addressDao;

    @Test
    public void getAddressDataTest() {
        Long id = 0L;
        Address address = new Address();
        address.setId(id);

        when(addressDao.findById(id)).thenReturn(Optional.of(address));
        Address found = addressService.getAddressData(id);
        assertEquals(found.getId(), id);
    }

    @Test
    public void getAddressDataNotFoundTest() {
        Long id = 0L;

        when(addressDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(AddressNotFoundException.class, () -> addressService.getAddressData(id));
    }

    @Test
    public void saveAddressDataTest() {
        Address address = new Address();
        address.setId(0L);

        when(addressDao.save(address)).thenReturn(address);
        Address saved = addressService.saveAddressData(address);
        assertEquals(saved, address);
    }
}
