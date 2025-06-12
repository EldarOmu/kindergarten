package kg.mega.kindergarten.models.dtos;

import java.util.Date;
import java.util.List;

public record ChildUpdateDto(
        String firstName,
        String lastName,
        GroupDto groupDto,
        Date dateOfBirth,
        List<ParentDto> parentDtos,
        boolean active
) {
}
