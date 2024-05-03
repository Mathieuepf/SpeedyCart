package fr.epf.speedycart.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Client {
    @Id
    private long clientId;
    private String firstname;
    private String lastname;
    private Date active_from;
    private Date deactive_from;
    private Date client_DOB;
}
