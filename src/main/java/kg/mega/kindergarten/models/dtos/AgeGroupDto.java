package kg.mega.kindergarten.models.dtos;

public record AgeGroupDto(
        Long ageGroupId,
        String name,
        double price,
        boolean active
) {
}
