package codesquad.airbnb.accomodation.service;

import codesquad.airbnb.accomodation.dto.PriceAndCount;
import codesquad.airbnb.accomodation.dto.PriceAndCountStatistics;
import codesquad.airbnb.accomodation.repository.AccommodationQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AccommodationStatisticsServiceImpl implements AccommodationStatisticsService {

    private static final ConcurrentHashMap<LocalDate, PriceAndCountStatistics> simpleCache = new ConcurrentHashMap<>();

    private final AccommodationQueryRepository accommodationQueryRepository;
    private final AmountUnitPolicy amountUnitPolicy;

    @Override
    public PriceAndCountStatistics getCachedPriceAndCountStatistics() {
        LocalDate key = LocalDate.now();

        if (isNotCached(key)) {
            simpleCache.clear();
            updateCache(key);
        }

        return simpleCache.get(key);
    }

    @Override
    public AmountUnitPolicy getAmountUnitPolicy() {
        return amountUnitPolicy;
    }

    private boolean isNotCached(LocalDate key) {
        return !simpleCache.containsKey(key);
    }

    private void updateCache(LocalDate key) {
        List<PriceAndCount> priceAndCountList = findPriceAndCountStatistics(amountUnitPolicy.getAmount());
        double averagePrice = accommodationQueryRepository.findAveragePrice();

        PriceAndCountStatistics priceAndCountStatistics = new PriceAndCountStatistics(priceAndCountList, averagePrice);
        simpleCache.put(key, priceAndCountStatistics);
    }

    private List<PriceAndCount> findPriceAndCountStatistics(long amountUnit) {
        return accommodationQueryRepository.findPriceAndCountStatisticsByAmountUnit(amountUnit);
    }
}
