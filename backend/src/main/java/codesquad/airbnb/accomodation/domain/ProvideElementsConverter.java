package codesquad.airbnb.accomodation.domain;

import javax.persistence.AttributeConverter;

public class ProvideElementsConverter implements AttributeConverter<ProvideElement, String> {

    @Override
    public String convertToDatabaseColumn(ProvideElement attribute) {
        return attribute.getName();
    }

    @Override
    public ProvideElement convertToEntityAttribute(String dbData) {
        return ProvideElement.provideNameOf(dbData);
    }
}
