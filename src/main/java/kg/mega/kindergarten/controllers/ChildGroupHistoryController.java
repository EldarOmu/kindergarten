package kg.mega.kindergarten.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryCreateDto;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryDto;
import kg.mega.kindergarten.services.ChildGroupHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ape/child-group-history")
public class ChildGroupHistoryController {
    private final ChildGroupHistoryService childGroupHistoryService;

    public ChildGroupHistoryController(ChildGroupHistoryService childGroupHistoryService) {
        this.childGroupHistoryService = childGroupHistoryService;
    }

    @PostMapping("/create")
    @Operation(summary = "Создание групповой истории ребенка", description = "Создание групповой истории ребенка и сохранение в базу данных")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> createChildGroupHistory(@RequestBody ChildGroupHistoryCreateDto childGroupHistoryCreateDto) {
        return ResponseEntity.ok(childGroupHistoryService.create(childGroupHistoryCreateDto));
    }

    @GetMapping("/get-child-group-history/{childId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Найти группу историй ребенка", description = "Находим группу историй ребенка спомощью childId")
    public ResponseEntity<?> getChildGroupHistoryByChildId(@PathVariable Long childId) {
        return ResponseEntity.ok(childGroupHistoryService.getChildGroupHistory(childId));
    }

    @PutMapping("/end-current-child-history-group/{childId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @Operation(summary = "Закончить текущую историю пребывания ребенка в группе", description = "Заканчиваем текущую историю пребывания ребенка в группе обозначая дату в момент использования метода")
    public ResponseEntity<?> endCurrentChildGroupHistory(@PathVariable Long childId) {
        ChildGroupHistoryDto childGroupHistoryDto = childGroupHistoryService.endCurrentChildGroupHistory(childId);
        return ResponseEntity.ok(childGroupHistoryDto);
    }
}
