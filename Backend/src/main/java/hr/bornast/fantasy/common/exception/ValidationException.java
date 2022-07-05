package hr.bornast.fantasy.common.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

    public ValidationException() {
        super();
    }

    public ValidationException(final String message) {
        super(message);
    }

    public ValidationException(final String key, final String value) {
        super();
        addErrors(key, value);
    }

    public ValidationException(final Map<String, Object> errors) {
        super();
        errors.forEach(this::addErrors);
    }

    private Map<String, Object> errors = new HashMap<>();

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }

    public void addErrors(String key, Object value) {
        errors.put(key, value);
    }
}
