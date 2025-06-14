package kg.mega.kindergarten.services;

import kg.mega.kindergarten.enums.Position;
import kg.mega.kindergarten.models.Group;
import kg.mega.kindergarten.models.dtos.GroupCreateDto;
import kg.mega.kindergarten.models.dtos.GroupDto;
import kg.mega.kindergarten.models.dtos.GroupUpdateDto;

import java.util.List;

public interface GroupService {
    GroupDto createGroup(GroupCreateDto dto);

    GroupDto getGroup(Long id);

    List<GroupDto> getAll(int page, int size);

    GroupDto updateGroup(Long id, GroupUpdateDto uDTO);

    boolean deleteGroup(Long id);

    Group findById(Long id);
}
