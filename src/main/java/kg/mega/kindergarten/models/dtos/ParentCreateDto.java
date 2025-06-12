package kg.mega.kindergarten.models.dtos;

import kg.mega.kindergarten.enums.Role;

public record ParentCreateDto(
        String firstName,
        String lastName,
        Role role,
        ContactDto contactDto
) {
}
