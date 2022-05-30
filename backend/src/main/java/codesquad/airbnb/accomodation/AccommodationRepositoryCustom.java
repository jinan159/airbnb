package codesquad.airbnb.accomodation;

import codesquad.airbnb.accomodation.dto.PriceAndCount;

import java.util.List;

public interface AccommodationRepositoryCustom {
    List<PriceAndCount> findPriceAndCountStatisticsByAmountUnit(long amountUnit);
}
