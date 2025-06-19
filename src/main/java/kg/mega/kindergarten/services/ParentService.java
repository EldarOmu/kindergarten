package kg.mega.kindergarten.services;

import kg.mega.kindergarten.models.Parent;
import kg.mega.kindergarten.models.dtos.ParentCreateDto;
import kg.mega.kindergarten.models.dtos.ParentDetailDto;
import kg.mega.kindergarten.models.dtos.ParentDto;
import kg.mega.kindergarten.models.dtos.ParentUpdateDto;

import java.util.List;

public interface ParentService {
    ParentDto create(ParentCreateDto dto);

    ParentDto getParent(Long id);

    List<ParentDto> getAll(int page, int size);

    ParentDto update(Long id, ParentUpdateDto uDTO);

    boolean delete(Long id);

    List<Parent> findAllById(List<Long> parentIds);

    ParentDto changeParentDetail(Long parentId, ParentDetailDto parentDetailDto);
}
