package codesquad.airbnb.accomodation.service;

import org.springframework.stereotype.Component;

@Component
public class AmountUnitPolicy {

    private static final long AMOUNT_UNIT = 10_000L;

    public long getAmount() {
        return AMOUNT_UNIT;
    }
}
