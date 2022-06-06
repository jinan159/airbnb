package codesquad.airbnb.accomodation.dto;

import codesquad.airbnb.accomodation.domain.ProvideName;
import lombok.Getter;

@Getter
public class ProvideResponse {
    private String name;
    private int count;

    public ProvideResponse(ProvideName provideName, int count) {
        this.name = provideName.getName();
        this.count = count;
    }
}
