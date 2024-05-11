package fr.epf.speedycart.api.exception;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException() {
        super();
    }

    public ShopNotFoundException(String message) {
        super(message);
    }
}