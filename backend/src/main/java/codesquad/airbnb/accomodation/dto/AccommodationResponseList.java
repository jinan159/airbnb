package codesquad.airbnb.accomodation.dto;

import codesquad.airbnb.accomodation.domain.Accommodation;

import java.util.List;
import java.util.stream.Collectors;

public class AccommodationResponseList {

    private List<AccommodationResponse> responses;

    public static AccommodationResponseList from(List<Accommodation> accommodations) {
        AccommodationResponseList responseList = new AccommodationResponseList();

        responseList.responses = accommodations.stream()
                .map(AccommodationResponse::of)
                .collect(Collectors.toUnmodifiableList());

        return responseList;
    }

    public List<AccommodationResponse> getResponses() {
        return List.copyOf(responses);
    }
}
