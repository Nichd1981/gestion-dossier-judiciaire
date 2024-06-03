package be.tftic.java.bll.exceptions.user;

public class UserDeniedAccessException extends UserException {

    public UserDeniedAccessException() {
        super("Access denied");
    }

    public UserDeniedAccessException(String message) {
        super(message);
    }

}
