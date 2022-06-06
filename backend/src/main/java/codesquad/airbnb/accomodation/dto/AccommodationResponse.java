package codesquad.airbnb.accomodation.dto;

import codesquad.airbnb.accomodation.domain.Accommodation;
import codesquad.airbnb.accomodation.domain.AccommodationProvide;
import codesquad.airbnb.accomodation.domain.Address;
import lombok.Getter;

import java.util.List;

@Getter
public class AccommodationResponse {

    private long id;
    private String title;
    private long price;
    private String imageUrl;
    private double x;
    private double y;
    private Address address;
    private List<AccommodationProvide> accommodationProvideList;

    public static AccommodationResponse of(Accommodation accommodation) {
        AccommodationResponse accommodationResponse = new AccommodationResponse();

        accommodationResponse.id = accommodation.getId();
        accommodationResponse.title = accommodation.getTitle();
        accommodationResponse.price = accommodation.getPrice();
        accommodationResponse.imageUrl = (accommodation.getImage() != null) ? accommodation.getImage().getImageUrl() : "";
        accommodationResponse.x = (accommodation.getPoint() != null) ? accommodation.getPoint().getX() : 0;
        accommodationResponse.y = (accommodation.getPoint() != null) ? accommodation.getPoint().getY() : 0;
        accommodationResponse.address = accommodation.getAddress();
        accommodationResponse.accommodationProvideList = accommodation.getAccommodationProvides();

        return accommodationResponse;
    }

}
