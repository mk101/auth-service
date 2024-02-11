package kolesov.maksim.mapping.auth.exception;

public class UnauthorizedException extends ServiceException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

}
