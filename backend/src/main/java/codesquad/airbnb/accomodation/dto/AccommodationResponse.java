package codesquad.airbnb.accomodation.dto;

import codesquad.airbnb.accomodation.domain.Accommodation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class AccommodationResponse {

    private long id;
    private String title;
    private long price;
    private String imageUrl;
    private double x;
    private double y;
    private String address;
    private List<ProvideResponse> provides;

    public static AccommodationResponse of(Accommodation accommodation) {
        List<ProvideResponse> provideResponses = accommodation.getAccommodationProvides()
                .stream()
                .map(ap -> new ProvideResponse(ap.getProvide().getName(), ap.getCount()))
                .collect(Collectors.toUnmodifiableList());

        return new AccommodationResponse(
                accommodation.getId(),
                accommodation.getTitle(),
                accommodation.getPrice(),
                (accommodation.getImage() != null) ? accommodation.getImage().getImageUrl() : "",
                (accommodation.getPoint() != null) ? accommodation.getPoint().getX() : 0,
                (accommodation.getPoint() != null) ? accommodation.getPoint().getY() : 0,
                accommodation.getAddress().toString(),
                provideResponses
        );
    }

}
