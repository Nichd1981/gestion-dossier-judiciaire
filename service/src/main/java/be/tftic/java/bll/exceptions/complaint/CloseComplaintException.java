package be.tftic.java.bll.exceptions.complaint;

import be.tftic.java.bll.exceptions.ApplicationException;

public class CloseComplaintException extends ApplicationException {

    public CloseComplaintException(String message) {
        super(message);
    }

    public CloseComplaintException() {
        super("Complaint must be \"In progress\" to be closed");
    }
}
