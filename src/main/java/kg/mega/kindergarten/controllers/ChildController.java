package kg.mega.kindergarten.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.mega.kindergarten.models.dtos.ChildChangeDetail;
import kg.mega.kindergarten.models.dtos.ChildCreateDto;
import kg.mega.kindergarten.models.dtos.ChildDto;
import kg.mega.kindergarten.models.dtos.ChildUpdateDto;
import kg.mega.kindergarten.services.ChildService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/child")
public class ChildController implements CRUDOperations<ChildDto, ChildCreateDto, Long, ChildUpdateDto>{
    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @Override
    @Operation(summary = "Создание ребенка", description = "Создаение ребенка и сохранение в базу данных")
    @PreAuthorize("hasRole('ADMIN')")
    public ChildDto create(ChildCreateDto dto) {
        return childService.create(dto);
    }

    @Override
    @Operation(summary = "Найти ребенка", description = "Находим ребенка спомощью id из базы данных")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ChildDto read(Long id) {
        return childService.getChild(id);
    }

    @Override
    @Operation(summary = "Найти всех детей", description = "Находим список всех детей спомощью Pageable")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ChildDto> readAll(int page, int size) { return childService.getAll(page, size);
    }

    @Override
    @Operation(summary = "Обновить ребенка", description = "Обновляем ребенка спомощью id")
    @PreAuthorize("hasRole('ADMIN')")
    public ChildDto update(Long id, ChildUpdateDto uDTO) {
        return childService.updateChild(id, uDTO);
    }

    @Override
    @Operation(summary = "Удаляем ребенка", description = "Удаляем ребенка спомощью изменения поля active на false")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean delete(Long id) {
        return childService.deleteChild(id);
    }

    @PutMapping("/change-child-group/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Поменять группу ребенка", description = "Меняем группу ребенка через id ребенка и id группы")
    public ResponseEntity<?> changeChildGroup(@PathVariable Long id, @RequestParam Long groupId){
        return ResponseEntity.ok(childService.changeChildGroup(id, groupId));
    }

    @PutMapping("/change-child-details/{id}")
    @Operation(summary = "Изменение пользовательских данных самим пользователем", description = "Изменение пользовательских данных спомощью id ребенка и тела ChildChangeDetail")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeChildDetails(@PathVariable Long id, @RequestBody ChildChangeDetail childChangeDetail){
        return ResponseEntity.ok(childService.changeChildDetails(id, childChangeDetail));
    }

 }
