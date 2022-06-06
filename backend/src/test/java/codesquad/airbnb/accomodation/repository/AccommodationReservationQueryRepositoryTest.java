package codesquad.airbnb.accomodation.repository;

import codesquad.airbnb.accomodation.domain.Accommodation;
import codesquad.airbnb.config.QueryDSLConfig;
import codesquad.airbnb.member.Member;
import codesquad.airbnb.member.MemberRepository;
import codesquad.airbnb.reservation.Reservation;
import codesquad.airbnb.reservation.ReservationRepository;
import codesquad.airbnb.reservation.Schedule;
import codesquad.airbnb.reservation.StartEndDate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QueryDSLConfig.class)
class AccommodationReservationQueryRepositoryTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private AccommodationReservationQueryRepository repository;

    @BeforeEach
    void setUp() {
        repository = new AccommodationReservationQueryRepository(jpaQueryFactory);

        accommodationRepository.deleteAll();
        reservationRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void 예약된_일정이_일치하면_숙소가_반환되지_않는다 () {
        // given
        LocalDate reservedStartDate = LocalDate.of(2022, 6, 1);
        LocalDate reservedEndDate = LocalDate.of(2022, 6, 3);

        prepareReservation(reservedStartDate, reservedEndDate);

        // when
        List<Accommodation> notReservedAccommodations = repository.findNotReservedAccommodations(createNewSchedule(reservedStartDate, reservedEndDate));

        // then
        assertThat(notReservedAccommodations).isNotNull();
        assertThat(notReservedAccommodations).isEmpty();
    }

    @Test
    void 예약된_일정과_시작일만_겹쳐도_숙소가_반환되지_않는다 () {
        // given
        LocalDate reservedStartDate = LocalDate.of(2022, 6, 1);
        LocalDate reservedEndDate = LocalDate.of(2022, 6, 3);

        prepareReservation(reservedStartDate, reservedEndDate);

        LocalDate startDate = LocalDate.of(2022, 6,2);
        LocalDate endDate = LocalDate.of(2022, 6, 4);

        // when
        List<Accommodation> notReservedAccommodations = repository.findNotReservedAccommodations(createNewSchedule(startDate, endDate));

        // then
        assertThat(notReservedAccommodations).isNotNull();
        assertThat(notReservedAccommodations).isEmpty();
    }

    @Test
    void 예약된_일정과_종료일만_겹쳐도_숙소가_반환되지_않는다 () {
        // given
        LocalDate reservedStartDate = LocalDate.of(2022, 6, 1);
        LocalDate reservedEndDate = LocalDate.of(2022, 6, 3);

        prepareReservation(reservedStartDate, reservedEndDate);

        LocalDate startDate = LocalDate.of(2022, 5,30);
        LocalDate endDate = LocalDate.of(2022, 6,2);

        // when
        List<Accommodation> notReservedAccommodations = repository.findNotReservedAccommodations(createNewSchedule(startDate, endDate));

        // then
        assertThat(notReservedAccommodations).isNotNull();
        assertThat(notReservedAccommodations).isEmpty();
    }

    @Test
    void 숙소_예약이_겹치지_않으면_예약_가능한_숙소_리스트를_반환한다 () {
        // given
        LocalDate reservedStartDate = LocalDate.of(2022, 6, 1);
        LocalDate reservedEndDate = LocalDate.of(2022, 6, 3);

        prepareReservation(reservedStartDate, reservedEndDate);

        LocalDate startDate = LocalDate.of(2022, 6,4);
        LocalDate endDate = LocalDate.of(2022, 6,6);

        // when
        List<Accommodation> notReservedAccommodations = repository.findNotReservedAccommodations(createNewSchedule(startDate, endDate));

        // then
        assertThat(notReservedAccommodations).isNotNull();
        assertThat(notReservedAccommodations).isNotEmpty();
    }

    private Schedule createNewSchedule(LocalDate startDate, LocalDate endDate) {
        return Schedule.of(startDate, endDate, null);
    }

    private void prepareReservation(LocalDate startDate, LocalDate endDate) {
        Accommodation savedAccommodation = accommodationRepository.save(createNewAccommodation());
        Member savedMember = memberRepository.save(createNewMember());
        reservationRepository.save(createNewReservation(savedAccommodation, savedMember, startDate, endDate));
    }

    private Accommodation createNewAccommodation() {
        return new Accommodation(null, "test hotel", 0L, null, null, null, null);
    }

    private Reservation createNewReservation(Accommodation accommodation, Member member, LocalDate startDate, LocalDate endDate) {
        StartEndDate startEndDate = new StartEndDate(startDate, endDate);
        return new Reservation(null, accommodation, member, new Schedule(startEndDate, null));
    }

    private Member createNewMember() {
        return new Member(null, "jwkim@email.com", null);
    }

}