package codesquad.airbnb.accomodation.service;

import codesquad.airbnb.accomodation.dto.PriceAndCount;
import codesquad.airbnb.accomodation.dto.PriceAndCountStatistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccommodationStatisticsServiceTest {

    @Autowired
    private AccommodationStatisticsService dummyAccommodationStatisticsService;

    @Test
    @Sql("classpath:sql/accommodation-statistics.sql")
    void 정해진_단위의_통계를_출력한다 () {
        // given
        long amountUnit = dummyAccommodationStatisticsService.getAmountUnitPolicy().getAmount();

        // when
        PriceAndCountStatistics cachedPriceAndCountStatistics = dummyAccommodationStatisticsService.getCachedPriceAndCountStatistics();

        // then
        assertThat(cachedPriceAndCountStatistics).isNotNull();
        assertThat(cachedPriceAndCountStatistics.getPriceAndCountList()).isNotEmpty();

        for (PriceAndCount priceAndCount : cachedPriceAndCountStatistics) {
            assertThat(priceAndCount).isNotNull();
            shouldModResultZero(priceAndCount.getPriceRange(), amountUnit);
        }
    }

    private void shouldModResultZero(long priceRange, long amountUnit) {
        assertThat(priceRange % amountUnit).isEqualTo(0);
    }
}