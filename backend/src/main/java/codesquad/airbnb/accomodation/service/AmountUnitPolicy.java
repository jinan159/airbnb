package codesquad.airbnb.accomodation.service;

import org.springframework.stereotype.Component;

@Component
public class AmountUnitPolicy {

    private static final long amountUnit = 10_000L;

    public long getAmount() {
        return amountUnit;
    }
}
