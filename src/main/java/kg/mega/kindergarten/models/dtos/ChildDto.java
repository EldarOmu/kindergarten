package kg.mega.kindergarten.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record ChildDto(
        Long childId,
        String firstName,
        String lastName,
        GroupDto groupDto,
        @JsonFormat(pattern = "dd.MM.yyyy")
        Date dateOfBirth,
        List<ParentDto> parentDtos
) {
}
