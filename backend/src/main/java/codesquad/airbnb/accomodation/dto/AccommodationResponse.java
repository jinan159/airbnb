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
        // TODO : 2022-06-07 provideResponses 를 provideResponses 내부에서 생성하지 않고, 외부에서 주입하도록 변경
        // TODO : 2022-06-07 provideResponses 가 결국 N+1 쿼리를 발생시키는데, 이를 하나의 쿼리로 가져와서 조합하도록 변경하기
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
