package codesquad.airbnb.reservation;

import codesquad.airbnb.accomodation.exception.InvalidStartEndDateException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.util.comparator.Comparators;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor // for @Embeddable
public class StartEndDate {

    private LocalDate startDate;
    private LocalDate endDate;

    public StartEndDate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;

        if (startDate.isAfter(endDate)) throw new InvalidStartEndDateException();
    }
}
