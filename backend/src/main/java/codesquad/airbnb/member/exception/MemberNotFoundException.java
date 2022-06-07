package codesquad.airbnb.member.exception;

import codesquad.airbnb.exception.AirbnbRuntimeException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends AirbnbRuntimeException {

    public MemberNotFoundException() {
        super("존재하지 않는 회원입니다.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
