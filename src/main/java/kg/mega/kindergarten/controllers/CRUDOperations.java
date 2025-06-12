package kg.mega.kindergarten.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CRUDOperations<DTO, CDTO, ID, UDTO> {
    @PostMapping("/create")
    DTO create(@RequestBody CDTO dto);
    @GetMapping("/{id}")
    DTO read(@PathVariable ID id);
    @GetMapping("/list")
    List<DTO> readAll(@RequestParam int page, @RequestParam int size);
    @PutMapping("/update/{id}")
    DTO update(@PathVariable ID id, @RequestBody UDTO uDTO);
    @DeleteMapping("/delete")
    boolean delete(@RequestParam ID id);
}
