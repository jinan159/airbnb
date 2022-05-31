package codesquad.airbnb.exception;

import org.springframework.http.HttpStatus;

public abstract class AirbnbRuntimeException extends RuntimeException {

    public AirbnbRuntimeException(String message) {
        super(message);
    }

    public AirbnbRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract HttpStatus getStatus();
}
