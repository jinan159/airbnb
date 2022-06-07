package codesquad.airbnb.accomodation.service;

import codesquad.airbnb.accomodation.dto.PriceAndCountStatistics;

public interface AccommodationStatisticsService {

    PriceAndCountStatistics getCachedPriceAndCountStatistics();
    AmountUnitPolicy getAmountUnitPolicy();
}