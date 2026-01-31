package ai.neuron.copilot.knowledge.foundation.data.rdb;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDateTime;

@Converter(autoApply = true)
public class DeletedAtConverter implements AttributeConverter<Boolean, LocalDateTime> {

    @Override
    public LocalDateTime convertToDatabaseColumn(Boolean attribute) {
        return Boolean.TRUE.equals(attribute) ? null : LocalDateTime.now();
    }

    @Override
    public Boolean convertToEntityAttribute(LocalDateTime dbData) {
        return dbData == null;
    }
}