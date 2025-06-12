package kg.mega.kindergarten.models.dtos;

public record ContactDto(
        Long contactId,
        String phoneNumber,
        String secondPhoneNumber,
        String email
) {
}
