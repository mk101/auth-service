package kolesov.maksim.mapping.auth.exception;

public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
