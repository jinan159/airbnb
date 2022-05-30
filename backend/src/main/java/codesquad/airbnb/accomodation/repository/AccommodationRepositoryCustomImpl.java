package codesquad.airbnb.accomodation.repository;

import codesquad.airbnb.accomodation.domain.QAccommodation;
import codesquad.airbnb.accomodation.dto.PriceAndCount;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccommodationRepositoryCustomImpl implements AccommodationRepositoryCustom {

    private final static QAccommodation accommodation = QAccommodation.accommodation;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PriceAndCount> findPriceAndCountStatisticsByAmountUnit(long amountUnit) {
        NumberExpression<Long> priceCutByAmountUnit = accommodation.price.divide(amountUnit).multiply(amountUnit);
        NumberExpression<Integer> countAll = accommodation.count().intValue();

        return queryFactory.select(
                Projections.constructor(PriceAndCount.class,
                        priceCutByAmountUnit,
                        countAll
                ))
                .from(accommodation)
                .groupBy(priceCutByAmountUnit)
                .orderBy(priceCutByAmountUnit.asc())
                .fetch();
    }
}
