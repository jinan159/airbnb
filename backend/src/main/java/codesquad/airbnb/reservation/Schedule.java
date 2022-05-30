package codesquad.airbnb.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor // for @Embeddable
public class Schedule {

    @Embedded
    private StartEndDate startEndDate;
    private int visitors;
}
