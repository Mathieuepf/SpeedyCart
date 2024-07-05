package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.model.Address;

public interface AddressService {
    Address getAddressData(Long id);

    Address saveAddressData(Address address);
}
