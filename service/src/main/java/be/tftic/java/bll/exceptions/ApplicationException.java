package be.tftic.java.bll.exceptions;

public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Exception e) {
        super(message, e);
    }

    @Override
    public String toString() {
        StackTraceElement element = this.getStackTrace()[0];
        return this.getClass().getSimpleName() + " throw in " + element.getFileName() + " in method " + element.getMethodName() + " at line number " + element.getLineNumber() + ".";
    }

}
