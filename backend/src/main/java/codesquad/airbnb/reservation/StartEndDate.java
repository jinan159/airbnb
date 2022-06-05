package codesquad.airbnb.reservation;

import codesquad.airbnb.accomodation.exception.InvalidStartEndDateException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.util.comparator.Comparators;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor // for @Embeddable
public class StartEndDate {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public StartEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;

        if (startDate.isAfter(endDate)) throw new InvalidStartEndDateException();
    }
}
