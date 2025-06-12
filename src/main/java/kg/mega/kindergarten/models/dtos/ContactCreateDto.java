package kg.mega.kindergarten.models.dtos;

public record ContactCreateDto(
        String phoneNumber,
        String secondPhoneNumber,
        String email
) {
}
