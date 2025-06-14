package kg.mega.kindergarten.services;

import kg.mega.kindergarten.enums.Position;
import kg.mega.kindergarten.models.Teacher;
import kg.mega.kindergarten.models.dtos.TeacherCreateDto;
import kg.mega.kindergarten.models.dtos.TeacherDto;
import kg.mega.kindergarten.models.dtos.TeacherUpdateDto;

import java.util.List;

public interface TeacherService {
    TeacherDto createTeacher(TeacherCreateDto teacherCreateDto);

    TeacherDto getTeacher(Long id);

    List<TeacherDto> getAll(int page, int size);

    TeacherDto update(Long id, TeacherUpdateDto uDTO);

    boolean delete(Long id);

    Teacher findById(Long id);

    Teacher findTeacherByIdAndPosition(Long id, Position position);
}
