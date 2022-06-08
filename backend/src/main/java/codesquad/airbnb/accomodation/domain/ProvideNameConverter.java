package codesquad.airbnb.accomodation.domain;

import javax.persistence.AttributeConverter;

public class ProvideNameConverter implements AttributeConverter<ProvideName, String> {

    @Override
    public String convertToDatabaseColumn(ProvideName attribute) {
        return attribute.getName();
    }

    @Override
    public ProvideName convertToEntityAttribute(String dbData) {
        return ProvideName.provideNameOf(dbData);
    }
}
