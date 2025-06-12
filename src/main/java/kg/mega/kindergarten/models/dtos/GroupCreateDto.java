package kg.mega.kindergarten.models.dtos;

public record GroupCreateDto(
        String name,
        Long teacherId,
        Long ageGroupId
) {
}
