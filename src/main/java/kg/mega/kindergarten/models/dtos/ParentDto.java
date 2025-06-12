package kg.mega.kindergarten.models.dtos;

import kg.mega.kindergarten.enums.Role;

public record ParentDto(
        Long parentId,
        String firstName,
        String lastName,
        Role role,
        ContactDto contactDto,
        boolean active
) {
}
