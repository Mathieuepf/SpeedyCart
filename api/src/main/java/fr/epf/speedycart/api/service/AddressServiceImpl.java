package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.AddressNotFoundException;
import fr.epf.speedycart.api.model.Address;
import fr.epf.speedycart.api.repository.AddressDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressDao addressDao;

    @Override
    public Address getAddressData(Long id) {
        return addressDao.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Invalid Id"));
    }

    @Override
    public Address saveAddressData(@Valid Address address) {
        address.setId(0L);
        return addressDao.save(address);
    }
}
