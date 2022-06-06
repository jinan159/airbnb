package codesquad.airbnb.accomodation.dto;

import codesquad.airbnb.accomodation.domain.Accommodation;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AccommodationResponseList {

    private List<AccommodationResponse> responses;

    public static AccommodationResponseList from(List<Accommodation> accommodations) {
        return new AccommodationResponseList(accommodations.stream()
                .map(AccommodationResponse::of)
                .collect(Collectors.toUnmodifiableList()));
    }

    public List<AccommodationResponse> getResponses() {
        return List.copyOf(responses);
    }
}
