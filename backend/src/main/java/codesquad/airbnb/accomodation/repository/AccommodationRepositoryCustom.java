package codesquad.airbnb.accomodation.repository;

import codesquad.airbnb.accomodation.dto.PriceAndCount;

import java.util.List;

public interface AccommodationRepositoryCustom {
    List<PriceAndCount> findPriceAndCountStatisticsByAmountUnit(long amountUnit);
}
