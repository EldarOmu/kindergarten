package kg.mega.kindergarten.services.impl;

import kg.mega.kindergarten.mappers.ChildGroupHistoryMapper;
import kg.mega.kindergarten.models.ChildGroupHistory;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryCreateDto;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryDto;
import kg.mega.kindergarten.repositories.ChildGroupHistoryRepo;
import kg.mega.kindergarten.services.ChildGroupHistoryService;
import kg.mega.kindergarten.services.ChildService;
import kg.mega.kindergarten.services.GroupService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChildGroupHistoryServiceImpl implements ChildGroupHistoryService {
    private final ChildGroupHistoryRepo childGroupHistoryRepo;
    private final GroupService groupService;
    private final ChildService childService;

    public ChildGroupHistoryServiceImpl(ChildGroupHistoryRepo childGroupHistoryRepo, GroupService groupService, ChildService childService) {
        this.childGroupHistoryRepo = childGroupHistoryRepo;
        this.groupService = groupService;
        this.childService = childService;
    }

    @Override
    public ChildGroupHistoryDto create(ChildGroupHistoryCreateDto childGroupHistoryCreateDto) {
        ChildGroupHistory childGroupHistory = ChildGroupHistoryMapper.INSTANCE.childGroupHistoryCreateDtoToChild(childGroupHistoryCreateDto, groupService, childService);
        childGroupHistoryRepo.save(childGroupHistory);
        return ChildGroupHistoryMapper.INSTANCE.childGroupHistoryToChildGroupHistoryDto(childGroupHistory);
    }

    @Override
    public ChildGroupHistoryDto getChildGroupHistory(Long childId) {
        ChildGroupHistory childGroupHistory = childGroupHistoryRepo.findById(childId).orElseThrow(() -> new RuntimeException("Child group history not found"));
        return ChildGroupHistoryMapper.INSTANCE.childGroupHistoryToChildGroupHistoryDto(childGroupHistory);
    }

    @Override
    public ChildGroupHistoryDto endCurrentChildGroupHistory(Long childId) {
        ChildGroupHistory childGroupHistory = childGroupHistoryRepo.findChildGroupHistoriesByChildId(childId);
        childGroupHistory.setEndDate(LocalDateTime.now());
        childGroupHistoryRepo.save(childGroupHistory);
        return ChildGroupHistoryMapper.INSTANCE.childGroupHistoryToChildGroupHistoryDto(childGroupHistory);
    }
}
