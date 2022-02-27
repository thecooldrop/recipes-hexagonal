package udarnicka.common.persistence;

import udarnicka.common.CanonicalName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CanonicalNameToStringJpaAttributeConverter implements AttributeConverter<CanonicalName, String> {


    @Override
    public String convertToDatabaseColumn(CanonicalName attribute) {
        return null;
    }

    @Override
    public CanonicalName convertToEntityAttribute(String dbData) {
        return null;
    }
}
