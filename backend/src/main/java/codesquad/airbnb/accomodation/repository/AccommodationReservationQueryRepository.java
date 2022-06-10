package codesquad.airbnb.accomodation.repository;

import codesquad.airbnb.accomodation.domain.Accommodation;
import codesquad.airbnb.accomodation.domain.ProvideName;
import codesquad.airbnb.accomodation.domain.QAccommodation;
import codesquad.airbnb.accomodation.domain.QAccommodationProvide;
import codesquad.airbnb.accomodation.domain.QProvide;
import codesquad.airbnb.reservation.QReservation;
import codesquad.airbnb.reservation.QStartEndDate;
import codesquad.airbnb.reservation.Schedule;
import codesquad.airbnb.reservation.StartEndDate;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccommodationReservationQueryRepository {

    private final static QAccommodation Q_ACCOMMODATION = QAccommodation.accommodation;
    private final static QReservation Q_RESERVATION = QReservation.reservation;
    private final static QProvide Q_PROVIDE = QProvide.provide;
    private final static QAccommodationProvide Q_ACCOMMODATION_PROVIDE = QAccommodationProvide.accommodationProvide;

    private final JPAQueryFactory queryFactory;

    public List<Accommodation> findNotReservedAccommodations(Schedule schedule) {
        JPAQuery<Accommodation> accommodationJPAQuery = queryFactory.selectFrom(Q_ACCOMMODATION);

        if (!isVisitorsNull(schedule.getVisitors())) {
            accommodationJPAQuery = accommodationJPAQuery
                    .join(Q_PROVIDE)
                    .on(Q_PROVIDE.name.eq(ProvideName.CAPACITY))
                    .join(Q_ACCOMMODATION_PROVIDE)
                    .on(Q_ACCOMMODATION_PROVIDE.accommodation.id.eq(Q_ACCOMMODATION.id)
                            .and(Q_ACCOMMODATION_PROVIDE.provide.id.eq(Q_PROVIDE.id)));
        }

        return accommodationJPAQuery
                .leftJoin(Q_RESERVATION)
                .on(Q_RESERVATION.accommodation.id.eq(Q_ACCOMMODATION.id))
                .where(
                        notBetweenStartEndDate(schedule.getStartEndDate()),
                        goeCount(schedule.getVisitors()))
                .fetch();
    }

    private Predicate notBetweenStartEndDate(StartEndDate startEndDate) {
        if (isStartEndDateNull(startEndDate)) return null;

        QStartEndDate startEndDateColumn = Q_RESERVATION.schedule.startEndDate;

        Predicate notBetweenStartEndDate = ExpressionUtils.and(
                startEndDateColumn.startDate.notBetween(startEndDate.getStartDate(), startEndDate.getEndDate()),
                startEndDateColumn.endDate.notBetween(startEndDate.getStartDate(), startEndDate.getEndDate()));

        Predicate startAndDateNull = ExpressionUtils.and(
                startEndDateColumn.startDate.isNull(),
                startEndDateColumn.endDate.isNull());

        return ExpressionUtils.or(notBetweenStartEndDate, startAndDateNull);
    }

    private BooleanExpression goeCount(Integer visitors) {
        if (isVisitorsNull(visitors)) return null;

        return Q_ACCOMMODATION_PROVIDE.count.goe(visitors);
    }

    private boolean isVisitorsNull(Integer visitors) {
        return visitors == null || visitors == 0;
    }

    private boolean isStartEndDateNull(StartEndDate startEndDate) {
        return startEndDate == null ||
                startEndDate.getStartDate() == null ||
                startEndDate.getEndDate() == null;
    }


}
