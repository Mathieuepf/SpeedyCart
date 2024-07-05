package fr.epf.speedycart.api.exception;

public class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException() {
        super();
    }

    public DeliveryNotFoundException(String message) {
        super(message);
    }
}
