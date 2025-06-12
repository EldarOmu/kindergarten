package kg.mega.kindergarten.services;

import kg.mega.kindergarten.models.AgeGroup;
import kg.mega.kindergarten.models.dtos.AgeGroupCreateDto;
import kg.mega.kindergarten.models.dtos.AgeGroupDto;
import kg.mega.kindergarten.models.dtos.AgeGroupUpdateDto;

import java.util.List;

public interface AgeGroupService {
    AgeGroupDto createAgeGroup(AgeGroupCreateDto dto);

    AgeGroupDto getAgeGroup(Long id);

    List<AgeGroupDto> getAll(int page, int size);

    AgeGroupDto updateAgeGroup(Long id, AgeGroupUpdateDto uDTO);

    boolean deleteAgeGroup(Long id);

    AgeGroup findById(Long id);
}
