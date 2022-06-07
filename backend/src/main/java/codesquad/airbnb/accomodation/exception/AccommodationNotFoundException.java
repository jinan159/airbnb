package codesquad.airbnb.accomodation.exception;

import codesquad.airbnb.exception.AirbnbRuntimeException;
import org.springframework.http.HttpStatus;

public class AccommodationNotFoundException extends AirbnbRuntimeException {

    public AccommodationNotFoundException() {
        super("존재하지 않는 숙소입니다.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
