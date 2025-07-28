package dev.ayub.student_management_system.model.enums.converters;


import dev.ayub.student_management_system.model.enums.defination.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// JPA Converter for ConfigStatusEnum
@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<StatusEnum, Integer> {

    // Converts Enum -> DB Integer
    @Override
    public Integer convertToDatabaseColumn(StatusEnum status) {
        if (status == null) {
            return null;
        }
        return status.getCode();
    }

    // Converts DB Integer -> Enum
    @Override
    public StatusEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return StatusEnum.fromCode(dbData);
    }
}
