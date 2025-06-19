package kg.mega.kindergarten.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.mega.kindergarten.models.dtos.ParentCreateDto;
import kg.mega.kindergarten.models.dtos.ParentDetailDto;
import kg.mega.kindergarten.models.dtos.ParentDto;
import kg.mega.kindergarten.models.dtos.ParentUpdateDto;
import kg.mega.kindergarten.services.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parent")
public class ParentController implements CRUDOperations<ParentDto, ParentCreateDto, Long, ParentUpdateDto>{
    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @Override
    @Operation(summary = "Создание родителя", description = "Создаение родителя и сохранение в базу данных")
    @PreAuthorize("hasRole('ADMIN')")
    public ParentDto create(ParentCreateDto dto) {
        return parentService.create(dto);
    }

    @Override
    @Operation(summary = "Найти родителя", description = "Находим родителя спомощью id из базы данных")
    @PreAuthorize("hasAnyRole('ADMIN', 'PARENT')")
    public ParentDto read(Long id) {
        return parentService.getParent(id);
    }

    @Override
    @Operation(summary = "Найти всех родителей", description = "Находим список всех родителей спомощью Pageable")
    @PreAuthorize("hasAnyRole('ADMIN', 'PARENT')")
    public List<ParentDto> readAll(int page, int size) {
        return parentService.getAll(page, size);
    }

    @Override
    @Operation(summary = "Обновить родителя", description = "Обновляем родителя спомощью id")
    @PreAuthorize("hasRole('ADMIN')")
    public ParentDto update(Long id, ParentUpdateDto uDTO) {
        return parentService.update(id, uDTO);
    }

    @Override
    @Operation(summary = "Удаляем родителя", description = "Удаляем родителя спомощью изменения поля active на false")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean delete(Long id) {
        return parentService.delete(id);
    }

    @PutMapping("/change-parent-details/{parentId}")
    @Operation(summary = "Изменение пользовательских данных самим пользователем", description = "Изменение пользовательских данных спомощью id родителя и тела ParentChangeDetail")
    @PreAuthorize("hasAnyRole('ADMIN', 'PARENT')")
    public ResponseEntity<?> changeParentDetails(@PathVariable Long  parentId, @RequestBody ParentDetailDto parentDetailDto) {
        return ResponseEntity.ok(parentService.changeParentDetail(parentId, parentDetailDto));
    }
}
