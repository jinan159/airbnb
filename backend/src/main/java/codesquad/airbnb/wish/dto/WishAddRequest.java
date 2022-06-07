package codesquad.airbnb.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WishAddRequest {

    private long accommodationId;
    private long memberId;
}
