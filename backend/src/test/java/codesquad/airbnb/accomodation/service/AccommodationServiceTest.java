package codesquad.airbnb.accomodation.service;


import codesquad.airbnb.accomodation.dto.AccommodationResponse;
import codesquad.airbnb.accomodation.dto.AccommodationResponseList;
import codesquad.airbnb.accomodation.dto.AccommodationSearchRequest;
import codesquad.airbnb.accomodation.repository.AccommodationReservationQueryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql("classpath:sql/accommodation-reservation.sql")
class AccommodationServiceTest {

    @Autowired
    private AccommodationReservationQueryRepository accommodationReservationQueryRepository;

    private AccommodationService accommodationService;

    @BeforeEach
    void setUp() {
        accommodationService = new AccommodationService(accommodationReservationQueryRepository);
    }

    @Test
    @Transactional
    void 겹치는_일정이_없고_숙소_인원수가_맞으면_숙소_목록을_반환한다() {
        // given
        LocalDate startDate = LocalDate.of(2022, 5, 1);
        LocalDate endDate = LocalDate.of(2022, 5, 5);
        int visitors = 3;

        // when
        AccommodationResponseList notReservedAccommodations =
                accommodationService.findNotReservedAccommodations(new AccommodationSearchRequest(startDate, endDate, visitors));

        // then
        assertThat(notReservedAccommodations).isNotNull();

        List<AccommodationResponse> responses = notReservedAccommodations.getResponses();
        assertThat(responses).isNotNull();
        assertThat(responses).isNotEmpty();
    }

    @Test
    void 겹치는_일정이_없고_숙소_인원수가_맞지_않으면_빈목록을_반환한다() {
        // given
        LocalDate startDate = LocalDate.of(2022, 5, 1);
        LocalDate endDate = LocalDate.of(2022, 5, 5);
        int visitors = 10;

        // when
        AccommodationResponseList notReservedAccommodations =
                accommodationService.findNotReservedAccommodations(new AccommodationSearchRequest(startDate, endDate, visitors));

        // then
        assertThat(notReservedAccommodations).isNotNull();

        List<AccommodationResponse> responses = notReservedAccommodations.getResponses();
        assertThat(responses).isNotNull();
        assertThat(responses).isEmpty();
    }

    @Test
    void 겹치는_일정이_있고_숙소_인원수가_맞지_않으면_빈목록을_반환한다() {
        // given
        LocalDate startDate = LocalDate.of(2022, 6, 1);
        LocalDate endDate = LocalDate.of(2022, 6, 3);
        int visitors = 10;

        // when
        AccommodationResponseList notReservedAccommodations =
                accommodationService.findNotReservedAccommodations(new AccommodationSearchRequest(startDate, endDate, visitors));

        // then
        assertThat(notReservedAccommodations).isNotNull();

        List<AccommodationResponse> responses = notReservedAccommodations.getResponses();
        assertThat(responses).isNotNull();
        assertThat(responses).isEmpty();
    }
}