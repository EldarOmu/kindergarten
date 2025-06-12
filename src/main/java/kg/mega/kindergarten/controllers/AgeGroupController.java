package kg.mega.kindergarten.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.mega.kindergarten.models.dtos.AgeGroupCreateDto;
import kg.mega.kindergarten.models.dtos.AgeGroupDto;
import kg.mega.kindergarten.models.dtos.AgeGroupUpdateDto;
import kg.mega.kindergarten.services.AgeGroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/age-group")
public class AgeGroupController implements CRUDOperations<AgeGroupDto, AgeGroupCreateDto, Long, AgeGroupUpdateDto>{
    private final AgeGroupService ageGroupService;

    public AgeGroupController(AgeGroupService ageGroupService) {
        this.ageGroupService = ageGroupService;
    }

    @Override
    @Operation(summary = "Создание возрастной группы", description = "Создаение возрастной группы и сохранение в базу данных")
    @PreAuthorize("hasRole('ADMIN')")
    public AgeGroupDto create(AgeGroupCreateDto dto) {
        return ageGroupService.createAgeGroup(dto);
    }

    @Override
    @Operation(summary = "Найти возрастную группу", description = "Находим возрастную группу спомощью id из базы данных")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public AgeGroupDto read(Long id) {
        return ageGroupService.getAgeGroup(id);
    }

    @Override
    @Operation(summary = "Найти все возрастные группы", description = "Находим список всех возрастных групп спомощью Pageable")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public List<AgeGroupDto> readAll(int page, int size) {
        return ageGroupService.getAll(page, size);
    }

    @Override
    @Operation(summary = "Обновить возрастную группу", description = "Обновляем возрастную группу спомощью id")
    @PreAuthorize("hasRole('ADMIN')")
    public AgeGroupDto update(Long id, AgeGroupUpdateDto uDTO) {
        return ageGroupService.updateAgeGroup(id, uDTO);
    }

    @Override
    @Operation(summary = "Удаляем возрастную группу", description = "Удаляем возрастную группу спомощью изменения поля active на false")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean delete(Long id) {
        return ageGroupService.deleteAgeGroup(id);
    }
}
