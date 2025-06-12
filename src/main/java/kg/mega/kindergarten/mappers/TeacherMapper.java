package kg.mega.kindergarten.mappers;

import kg.mega.kindergarten.models.Teacher;
import kg.mega.kindergarten.models.dtos.TeacherCreateDto;
import kg.mega.kindergarten.models.dtos.TeacherDto;
import kg.mega.kindergarten.models.dtos.TeacherUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ContactMapper.class)
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    @Mapping(source = "contactDto.contactId", target = "contact.id")
    Teacher teacherCreateDtoToTeacher(TeacherCreateDto teacherCreateDto);

    @Mapping(source = "id", target = "teacherId")
    @Mapping(source = "contact", target = "contactDto")
    TeacherDto teacherToTeacherDto(Teacher teacher);

    @Mapping(source = "id", target = "teacherId")
    List<TeacherDto> teachersToTeachersDto(List<Teacher> teachers);

    void updateTeacherFromDto(TeacherUpdateDto dto, @MappingTarget Teacher teacher);
}
