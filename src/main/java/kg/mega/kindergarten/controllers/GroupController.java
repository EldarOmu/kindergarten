package kg.mega.kindergarten.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.mega.kindergarten.models.dtos.GroupCreateDto;
import kg.mega.kindergarten.models.dtos.GroupDto;
import kg.mega.kindergarten.models.dtos.GroupUpdateDto;
import kg.mega.kindergarten.services.GroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController implements CRUDOperations<GroupDto, GroupCreateDto, Long, GroupUpdateDto>{
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    @Operation(summary = "Создание группы", description = "Создаение группы и сохранение в базу данных")
    @PreAuthorize("hasRole('ADMIN')")
    public GroupDto create(GroupCreateDto dto) {
        return groupService.createGroup(dto);
    }

    @Override
    @Operation(summary = "Найти группу", description = "Находим группу спомощью id из базы данных")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'PARENT')")
    public GroupDto read(Long id) {
        return groupService.getGroup(id);
    }

    @Override
    @Operation(summary = "Найти все группы", description = "Находим список всех групп спомощью Pageable")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public List<GroupDto> readAll(int page, int size) {
        return groupService.getAll(page, size);
    }

    @Override
    @Operation(summary = "Обновить группу", description = "Обновляем группу спомощью id")
    @PreAuthorize("hasRole('ADMIN')")
    public GroupDto update(Long id, GroupUpdateDto uDTO) {
        return groupService.updateGroup(id, uDTO);
    }

    @Override
    @Operation(summary = "Удаляем группу", description = "Удаляем группу спомощью изменения поля active на false")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean delete(Long id) {
        return groupService.deleteGroup(id);
    }
}
