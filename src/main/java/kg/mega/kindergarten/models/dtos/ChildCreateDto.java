package kg.mega.kindergarten.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record ChildCreateDto(
        String firstName,
        String lastName,
        Long groupId,
        @JsonFormat(pattern = "dd.MM.yyyy")
        Date dateOfBirth,
        List<Long> parentIds
) {
}
