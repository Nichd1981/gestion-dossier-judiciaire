package be.tftic.java.bll.exceptions.user;

import be.tftic.java.bll.exceptions.ApplicationException;

public class UserException extends ApplicationException {

    public UserException(String message) {
        super(message);
    }
}
