package dev.ayub.student_management_system.model.enums.converters;

import dev.ayub.student_management_system.model.enums.definaton.GenderEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderEnumConverter implements AttributeConverter<GenderEnum, Integer> {


    @Override
    public Integer convertToDatabaseColumn(GenderEnum gender) {
        if (gender == null) {
            return null;
        }
        return gender.getCode();
    }


    @Override
    public GenderEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return GenderEnum.fromCode(dbData);
    }
}
