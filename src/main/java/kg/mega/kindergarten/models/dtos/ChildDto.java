package kg.mega.kindergarten.models.dtos;

import java.util.Date;
import java.util.List;

public record ChildDto(
        Long childId,
        String firstName,
        String lastName,
        GroupDto groupDto,
        Date dateOfBirth,
        List<ParentDto> parentDtos
) {
}
