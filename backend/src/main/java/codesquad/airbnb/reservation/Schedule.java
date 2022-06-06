package codesquad.airbnb.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor // for @Embeddable
public class Schedule {

    @Embedded
    private StartEndDate startEndDate;
    private Integer visitors;

    public static Schedule of(LocalDate startDate, LocalDate endDate, Integer visitors) {
        return new Schedule(new StartEndDate(startDate, endDate), visitors);
    }
}
