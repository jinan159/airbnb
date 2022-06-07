package codesquad.airbnb.wish.dto;

import codesquad.airbnb.wish.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishResponse {

    private long id;
    private long accommodationId;
    private long memberId;

    public static WishResponse from(Wish wish) {
        return new WishResponse(
                wish.getId(),
                wish.getAccommodationId(),
                wish.getMemberId()
        );
    }
}
