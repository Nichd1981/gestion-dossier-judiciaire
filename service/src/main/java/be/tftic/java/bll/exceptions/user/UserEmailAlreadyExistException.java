package be.tftic.java.bll.exceptions.user;

import be.tftic.java.bll.exceptions.ApplicationException;

public class UserEmailAlreadyExistException extends UserException {

    private String className;
    private String methodName;
    private long lineNumber;

    public UserEmailAlreadyExistException() {
        super("User email already exist.");
    }

    public UserEmailAlreadyExistException(String message) {
        super(message);
    }

    public UserEmailAlreadyExistException(String message, String className, String methodName, long lineNumber) {
        super(message);
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " throw in " + className + " in method " + methodName + " at line number " + lineNumber + ".";
    }

}
