package kg.mega.kindergarten.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ChildGroupHistoryDto(
        Long childGroupHistoryId,
        GroupDto groupDto,
        @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
        LocalDateTime startDate,
        @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
        LocalDateTime endDate,
        ChildDto childDto,
        double price
) {
}
