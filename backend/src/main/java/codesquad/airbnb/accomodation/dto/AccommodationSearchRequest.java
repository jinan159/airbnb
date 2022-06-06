package codesquad.airbnb.accomodation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AccommodationSearchRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer visitors;
}
