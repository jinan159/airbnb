package codesquad.airbnb.accomodation.repository;

import codesquad.airbnb.accomodation.dto.PriceAndCount;
import codesquad.airbnb.config.QueryDSLConfig;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QueryDSLConfig.class)
class AccommodationRepositoryCustomImplTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    private AccommodationRepositoryCustom accommodationRepositoryCustom;

    @BeforeEach
    void setUp() {
        accommodationRepositoryCustom = new AccommodationRepositoryCustomImpl(jpaQueryFactory);
    }

    @Test
    @Sql("classpath:sql/accommodation-statistics.sql")
    void 가격대별_숙소_수_통계를_반환한다 () {
        // given
        long amountUnit = 10000L;

        // when
        List<PriceAndCount> priceAndCountList = accommodationRepositoryCustom.findPriceAndCountStatisticsByAmountUnit(amountUnit);

        // then
        assertThat(priceAndCountList).isNotNull();
        assertThat(priceAndCountList).isNotEmpty();
        assertThat(priceAndCountList).size().isEqualTo(2);
    }

}