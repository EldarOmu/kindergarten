package kg.mega.kindergarten.models.dtos;

import java.util.Date;
import java.util.List;

public record ChildUpdateDto(
        String firstName,
        String lastName,
        Long groupId,
        Date dateOfBirth,
        List<Long> parentIds,
        boolean active
) {
}
