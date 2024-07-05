package fr.epf.speedycart.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ClientAddress {
    @Id
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Id
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
