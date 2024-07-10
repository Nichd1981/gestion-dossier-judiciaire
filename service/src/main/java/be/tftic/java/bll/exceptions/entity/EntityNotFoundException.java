package be.tftic.java.bll.exceptions.entity;

import be.tftic.java.bll.exceptions.ApplicationException;

public class EntityNotFoundException extends ApplicationException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException() {
        super("Entity not found");
    }

}
