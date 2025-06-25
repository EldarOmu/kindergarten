package kg.mega.kindergarten.services.impl;

import kg.mega.kindergarten.mappers.AgeGroupMapper;
import kg.mega.kindergarten.models.AgeGroup;
import kg.mega.kindergarten.models.dtos.AgeGroupCreateDto;
import kg.mega.kindergarten.models.dtos.AgeGroupDto;
import kg.mega.kindergarten.models.dtos.AgeGroupUpdateDto;
import kg.mega.kindergarten.repositories.AgeGroupRepo;
import kg.mega.kindergarten.services.AgeGroupService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgeGroupServiceImpl implements AgeGroupService {
    private final AgeGroupRepo ageGroupRepo;

    public AgeGroupServiceImpl(AgeGroupRepo ageGroupRepo) {
        this.ageGroupRepo = ageGroupRepo;
    }

    @Override
    public AgeGroupDto createAgeGroup(AgeGroupCreateDto dto) {
        AgeGroup ageGroup = AgeGroupMapper.INSTANCE.ageGroupCreateDtoToAgeGroup(dto);
        ageGroupRepo.save(ageGroup);
        return AgeGroupMapper.INSTANCE.ageGroupToAgeGroupDto(ageGroup);
    }

    @Override
    public AgeGroupDto getAgeGroup(Long id) {
        AgeGroup ageGroup = ageGroupRepo.getAgeGroupByIdAndActive(id, true);
        return AgeGroupMapper.INSTANCE.ageGroupToAgeGroupDto(ageGroup);
    }

    @Override
    public List<AgeGroupDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<AgeGroup> ageGroups = ageGroupRepo.getAllByActiveTrue(pageable);
        return AgeGroupMapper.INSTANCE.ageGroupToAgeGroupDtoList(ageGroups);
    }

    @Override
    public AgeGroupDto updateAgeGroup(Long id, AgeGroupUpdateDto uDTO) {
        AgeGroup ageGroup = findById(id);
        AgeGroupMapper.INSTANCE.updateGroupAgeFromDto(uDTO, ageGroup);
        ageGroupRepo.save(ageGroup);
        return AgeGroupMapper.INSTANCE.ageGroupToAgeGroupDto(ageGroup);
    }

    @Override
    public boolean deleteAgeGroup(Long id) {
        AgeGroup ageGroup = findById(id);
        ageGroup.setActive(false);
        ageGroupRepo.save(ageGroup);
        return true;
    }

    @Override
    public AgeGroup findById(Long id) {
        return ageGroupRepo.findById(id).orElseThrow( () -> new RuntimeException("Age group not found!"));
    }


}
