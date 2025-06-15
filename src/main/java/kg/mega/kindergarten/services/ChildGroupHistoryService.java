package kg.mega.kindergarten.services;

import kg.mega.kindergarten.models.dtos.ChildGroupHistoryCreateDto;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryDto;

public interface ChildGroupHistoryService {
    ChildGroupHistoryDto create(ChildGroupHistoryCreateDto childGroupHistoryCreateDto);

    ChildGroupHistoryDto getChildGroupHistory(Long childId);

    ChildGroupHistoryDto endCurrentChildGroupHistory(Long childId);
}
