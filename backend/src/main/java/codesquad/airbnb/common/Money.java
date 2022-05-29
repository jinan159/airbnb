package codesquad.airbnb.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor // for @Embeddable
public class Money {
    public static final Money ZERO = new Money(0L);

    private Long amount;
}
