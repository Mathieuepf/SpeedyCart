package fr.epf.speedycart.api.exception;

public class DeliveryException extends RuntimeException {
    public DeliveryException() {
        super();
    }

    public DeliveryException(String message) {
        super(message);
    }
}
