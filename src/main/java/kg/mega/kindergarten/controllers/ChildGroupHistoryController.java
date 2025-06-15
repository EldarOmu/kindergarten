package kg.mega.kindergarten.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.mega.kindergarten.models.Child;
import kg.mega.kindergarten.models.ChildGroupHistory;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryCreateDto;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryDto;
import kg.mega.kindergarten.services.ChildGroupHistoryService;
import kg.mega.kindergarten.services.ChildService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createChildGroupHistory(@RequestBody ChildGroupHistoryCreateDto childGroupHistoryCreateDto) {
        return ResponseEntity.ok(childGroupHistoryService.create(childGroupHistoryCreateDto));
    }

    @GetMapping("/get-child-group-history/{childId}")
    @Operation(summary = "Найти группу историй ребенка", description = "Находим группу историй ребенка спомощью childId")
    public ResponseEntity<?> getChildGroupHistoryByChildId(@PathVariable Long childId) {
        return ResponseEntity.ok(childGroupHistoryService.getChildGroupHistory(childId));
    }

    @PutMapping("/end-current-child-history-group/{childId}")
    public ResponseEntity<?> endCurrentChildGroupHistory(@PathVariable Long childId) {
        ChildGroupHistoryDto childGroupHistoryDto = childGroupHistoryService.endCurrentChildGroupHistory(childId);
        return ResponseEntity.ok(childGroupHistoryDto);
    }
}
