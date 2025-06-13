package kg.mega.kindergarten.controllers;

import kg.mega.kindergarten.models.dtos.ChildGroupHistoryCreateDto;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryDto;
import kg.mega.kindergarten.services.ChildGroupHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ape/child-group-history")
public class ChildGroupHistoryController {
    private final ChildGroupHistoryService childGroupHistoryService;

    public ChildGroupHistoryController(ChildGroupHistoryService childGroupHistoryService) {
        this.childGroupHistoryService = childGroupHistoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createChildGroupHistory(@RequestBody ChildGroupHistoryCreateDto childGroupHistoryCreateDto) {
        return ResponseEntity.ok(childGroupHistoryService.create(childGroupHistoryCreateDto));
    }
}
