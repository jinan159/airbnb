package codesquad.airbnb.accomodation.dto;

import codesquad.airbnb.wish.dto.WishResponse;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class WishedAccommodationResponse {

    @JsonUnwrapped
    private AccommodationResponse accommodationResponse;
    private WishResponse wish;
}
