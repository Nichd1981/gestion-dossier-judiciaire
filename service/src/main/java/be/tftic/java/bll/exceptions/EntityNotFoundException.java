package be.tftic.java.bll.exceptions;

public class EntityNotFoundException extends ApplicationException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException() {
        super("Entity not found");
    }

}
