package kg.mega.kindergarten.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ChildGroupHistoryCreateDto(
        Long groupId,
        @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
        LocalDateTime startDate,
        @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
        LocalDateTime endDate,
        Long childId,
        double price
) {
}
