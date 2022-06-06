package codesquad.airbnb.accomodation.service;

import codesquad.airbnb.accomodation.domain.Accommodation;
import codesquad.airbnb.accomodation.dto.AccommodationResponseList;
import codesquad.airbnb.accomodation.dto.AccommodationSearchRequest;
import codesquad.airbnb.accomodation.repository.AccommodationReservationQueryRepository;
import codesquad.airbnb.reservation.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationReservationQueryRepository accommodationQueryRepository;

    @Transactional
    public AccommodationResponseList findNotReservedAccommodations(AccommodationSearchRequest searchRequest) {
        Schedule schedule =
                Schedule.of(searchRequest.getStartDate(), searchRequest.getEndDate(), searchRequest.getVisitors());

        List<Accommodation> notReservedAccommodations = accommodationQueryRepository.findNotReservedAccommodations(schedule);

        return AccommodationResponseList.from(notReservedAccommodations);
    }
}
