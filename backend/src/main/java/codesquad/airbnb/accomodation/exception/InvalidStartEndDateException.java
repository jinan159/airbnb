package codesquad.airbnb.accomodation.exception;

import codesquad.airbnb.exception.AirbnbRuntimeException;
import org.springframework.http.HttpStatus;

public class InvalidStartEndDateException extends AirbnbRuntimeException {

    public InvalidStartEndDateException() {
        super("올바른 시작, 종료일자가 아닙니다.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
