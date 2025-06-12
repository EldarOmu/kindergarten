package kg.mega.kindergarten.models.dtos;

public record GroupUpdateDto(
        String name,
        TeacherDto teacherDto,
        AgeGroupDto ageGroupDto,
        boolean active
) {
}
