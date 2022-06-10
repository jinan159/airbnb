package codesquad.airbnb.accomodation.repository;

import codesquad.airbnb.accomodation.domain.QAccommodation;
import codesquad.airbnb.accomodation.dto.PriceAndCount;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccommodationQueryRepository {

    private final static QAccommodation Q_ACCOMMODATION = QAccommodation.accommodation;

    private final JPAQueryFactory queryFactory;

    public List<PriceAndCount> findPriceAndCountStatisticsByAmountUnit(long amountUnit) {
        NumberExpression<Long> priceCutByAmountUnit = Q_ACCOMMODATION.price.divide(amountUnit).multiply(amountUnit);
        NumberExpression<Integer> countAll = Q_ACCOMMODATION.count().intValue();

        return queryFactory.select(
                Projections.constructor(PriceAndCount.class,
                        priceCutByAmountUnit,
                        countAll
                ))
                .from(Q_ACCOMMODATION)
                .groupBy(priceCutByAmountUnit)
                .orderBy(priceCutByAmountUnit.asc())
                .fetch();
    }

    public double findAveragePrice() {
        NumberExpression<Double> avg = Q_ACCOMMODATION.price.avg();

        return queryFactory.select(Q_ACCOMMODATION.price.avg())
                .from(Q_ACCOMMODATION)
                .fetchFirst();
    }
}
