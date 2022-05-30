package codesquad.airbnb.accomodation.repository;

import codesquad.airbnb.accomodation.domain.QAccommodation;
import codesquad.airbnb.accomodation.dto.PriceAndCount;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
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
        NumberExpression<Long> floorResultOfPriceDivAmountUnit =
                Expressions.numberTemplate(Long.class, "FLOOR({0} / {1}) * {2}", accommodation.price, amountUnit, amountUnit);

        NumberExpression<Integer> countAll =
                Expressions.numberTemplate(Integer.class, "COUNT(*)");

        return queryFactory.select(
                Projections.fields(PriceAndCount.class,
                        floorResultOfPriceDivAmountUnit.as("priceRange"),
                        countAll.as("accommodationCount")
                ))
                .from(accommodation)
                .groupBy(floorResultOfPriceDivAmountUnit)
                .orderBy(floorResultOfPriceDivAmountUnit.asc())
                .fetch();
    }
}
