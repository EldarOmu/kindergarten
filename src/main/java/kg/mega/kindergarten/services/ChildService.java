package kg.mega.kindergarten.services;

import kg.mega.kindergarten.models.Child;
import kg.mega.kindergarten.models.dtos.ChildCreateDto;
import kg.mega.kindergarten.models.dtos.ChildDto;
import kg.mega.kindergarten.models.dtos.ChildUpdateDto;

import java.util.List;

public interface ChildService {
    ChildDto create(ChildCreateDto dto);

    ChildDto getChild(Long id);

    List<ChildDto> getAll(int page, int size);

    ChildDto updateChild(Long id, ChildUpdateDto uDTO);

    boolean deleteChild(Long id);

    Child findById(Long id);
}
