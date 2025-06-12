package kg.mega.kindergarten.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.mega.kindergarten.models.dtos.TeacherCreateDto;
import kg.mega.kindergarten.models.dtos.TeacherDto;
import kg.mega.kindergarten.models.dtos.TeacherUpdateDto;
import kg.mega.kindergarten.services.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController  implements CRUDOperations<TeacherDto, TeacherCreateDto, Long, TeacherUpdateDto>{
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    @Operation(summary = "Создание учителя", description = "Создаение учителя и сохранение в базу данных")
    @PreAuthorize("hasRole('ADMIN')")
    public TeacherDto create(TeacherCreateDto dto) {
        return teacherService.createTeacher(dto);
    }

    @Override
    @Operation(summary = "Найти учителя", description = "Находим учителя спомощью id из базы данных")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public TeacherDto read(Long id) {
        return teacherService.getTeacher(id);
    }

    @Override
    @Operation(summary = "Найти всех учителей", description = "Находим список всех учителей спомощью Pageable")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public List<TeacherDto> readAll(int page, int size) {
        return teacherService.getAll(page, size);
    }

    @Override
    @Operation(summary = "Обновить учителя", description = "Обновляем учителя спомощью id")
    @PreAuthorize("hasRole('ADMIN')")
    public TeacherDto update(Long id, TeacherUpdateDto uDTO) {
        return teacherService.update(id, uDTO);
    }

    @Override
    @Operation(summary = "Удаляем учителя", description = "Удаляем учителя спомощью изменения поля active на false")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean delete(Long id) {
        return teacherService.delete(id);
    }
}
