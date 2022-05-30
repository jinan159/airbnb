package codesquad.airbnb.accomodation.service;

import codesquad.airbnb.accomodation.dto.PriceAndCount;
import codesquad.airbnb.accomodation.dto.PriceAndCountStatistics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyAccommodationStatisticsService implements AccommodationStatisticsService {

    private static final AmountUnitPolicy policy = new AmountUnitPolicy();

    @Override
    public PriceAndCountStatistics getCachedPriceAndCountStatistics() {
        return new PriceAndCountStatistics(List.of(
                new PriceAndCount(10000L, 350),
                new PriceAndCount(20000L, 500),
                new PriceAndCount(30000L, 750),
                new PriceAndCount(40000L, 700),
                new PriceAndCount(50000L, 650),
                new PriceAndCount(60000L, 800),
                new PriceAndCount(70000L, 950),
                new PriceAndCount(80000L, 750),
                new PriceAndCount(90000L, 800),
                new PriceAndCount(100000L, 900),
                new PriceAndCount(110000L, 950),
                new PriceAndCount(120000L, 900),
                new PriceAndCount(130000L, 400),
                new PriceAndCount(140000L, 450),
                new PriceAndCount(150000L, 350),
                new PriceAndCount(160000L, 350),
                new PriceAndCount(170000L, 290),
                new PriceAndCount(180000L, 400),
                new PriceAndCount(190000L, 450),
                new PriceAndCount(200000L, 350),
                new PriceAndCount(210000L, 350),
                new PriceAndCount(220000L, 290),
                new PriceAndCount(230000L, 330),
                new PriceAndCount(240000L, 350),
                new PriceAndCount(250000L, 200),
                new PriceAndCount(260000L, 150),
                new PriceAndCount(270000L, 120),
                new PriceAndCount(280000L, 80),
                new PriceAndCount(290000L, 30),
                new PriceAndCount(300000L, 15)
        ), 114468.1156);
    }

    @Override
    public AmountUnitPolicy getAmountUnitPolicy() {
        return policy;
    }
}
