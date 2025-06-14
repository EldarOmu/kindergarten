package kg.mega.kindergarten.models.dtos;

public record GroupDto(
        Long groupId,
        String name,
        TeacherDto teacherDto,
        TeacherDto assistantDto,
        AgeGroupDto ageGroupDto,
        boolean active
) {
}
