package codesquad.airbnb.reservation;

import codesquad.airbnb.accomodation.exception.InvalidStartEndDateException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor // for @Embeddable
public class StartEndDate {

    private LocalDate startDate;
    private LocalDate endDate;

    public StartEndDate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;

        if ((startDate != null && endDate != null) && startDate.isAfter(endDate)) throw new InvalidStartEndDateException();
    }
}
