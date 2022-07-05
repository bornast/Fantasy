package hr.bornast.fantasy.common.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(final String message) {
        super(message);
    }

}
