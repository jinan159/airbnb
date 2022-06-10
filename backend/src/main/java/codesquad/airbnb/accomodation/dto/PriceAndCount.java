package codesquad.airbnb.accomodation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor // for QueryDSL
public class PriceAndCount {
    private Long priceRange;
    private Integer accommodationCount;
}
