package codesquad.airbnb.accomodation.service;

import codesquad.airbnb.accomodation.domain.Accommodation;
import codesquad.airbnb.accomodation.dto.AccommodationResponse;
import codesquad.airbnb.accomodation.dto.AccommodationSearchRequest;
import codesquad.airbnb.accomodation.repository.AccommodationReservationQueryRepository;
import codesquad.airbnb.reservation.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationReservationQueryRepository accommodationQueryRepository;

    @Transactional
    public List<AccommodationResponse> findNotReservedAccommodations(AccommodationSearchRequest searchRequest) {
        Schedule schedule =
                Schedule.of(searchRequest.getStartDate(), searchRequest.getEndDate(), searchRequest.getVisitors());

        List<Accommodation> notReservedAccommodations = accommodationQueryRepository.findNotReservedAccommodations(schedule);

        return notReservedAccommodations.stream()
                .map(AccommodationResponse::of)
                .collect(Collectors.toUnmodifiableList());
    }
}
