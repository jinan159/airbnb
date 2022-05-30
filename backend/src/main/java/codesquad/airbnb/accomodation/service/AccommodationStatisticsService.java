package codesquad.airbnb.accomodation.service;

import codesquad.airbnb.accomodation.dto.PriceAndCount;
import codesquad.airbnb.accomodation.dto.PriceAndCountStatistics;
import codesquad.airbnb.accomodation.repository.AccommodationRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AccommodationStatisticsService {

    private static final ConcurrentHashMap<LocalDate, PriceAndCountStatistics> simpleCache = new ConcurrentHashMap<>();

    private final AccommodationRepositoryCustom accommodationRepositoryCustom;
    private final AmountUnitPolicy amountUnitPolicy;

    public PriceAndCountStatistics getCachedPriceAndCountStatistics() {
        LocalDate key = LocalDate.now();

        if (isNotCached(key)) {
            simpleCache.clear();
            updateCache(key);
        }

        return simpleCache.get(key);
    }

    public AmountUnitPolicy getAmountUnitPolicy() {
        return amountUnitPolicy;
    }

    private boolean isNotCached(LocalDate key) {
        return !simpleCache.containsKey(key);
    }

    private void updateCache(LocalDate key) {
        List<PriceAndCount> priceAndCountList = findPriceAndCountStatistics(amountUnitPolicy.getAmount());
        PriceAndCountStatistics priceAndCountStatistics = new PriceAndCountStatistics(priceAndCountList);
        simpleCache.put(key, priceAndCountStatistics);
    }

    private List<PriceAndCount> findPriceAndCountStatistics(long amountUnit) {
        return accommodationRepositoryCustom.findPriceAndCountStatisticsByAmountUnit(amountUnit);
    }
}
