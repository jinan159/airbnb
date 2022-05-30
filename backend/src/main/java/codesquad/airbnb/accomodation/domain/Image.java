package codesquad.airbnb.accomodation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor // for @Embeddable
public class Image {
    private String imageUrl;
}
