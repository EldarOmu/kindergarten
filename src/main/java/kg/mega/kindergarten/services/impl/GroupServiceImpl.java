package kg.mega.kindergarten.services.impl;

import kg.mega.kindergarten.enums.Position;
import kg.mega.kindergarten.mappers.AgeGroupMapper;
import kg.mega.kindergarten.mappers.GroupMapper;
import kg.mega.kindergarten.mappers.TeacherMapper;
import kg.mega.kindergarten.models.AgeGroup;
import kg.mega.kindergarten.models.Group;
import kg.mega.kindergarten.models.Teacher;
import kg.mega.kindergarten.models.dtos.GroupCreateDto;
import kg.mega.kindergarten.models.dtos.GroupDto;
import kg.mega.kindergarten.models.dtos.GroupUpdateDto;
import kg.mega.kindergarten.repositories.GroupRepo;
import kg.mega.kindergarten.services.AgeGroupService;
import kg.mega.kindergarten.services.GroupService;
import kg.mega.kindergarten.services.TeacherService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.GroupPrincipal;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepo groupRepo;
    private final AgeGroupService ageGroupService;
    private final TeacherService teacherService;

    public GroupServiceImpl(GroupRepo groupRepo, AgeGroupService ageGroupService, TeacherService teacherService) {
        this.groupRepo = groupRepo;
        this.ageGroupService = ageGroupService;
        this.teacherService = teacherService;
    }


    @Override
    public GroupDto createGroup(GroupCreateDto dto) {
        Group group = GroupMapper.INSTANCE.groupCreateDtoToGroup(dto, teacherService, ageGroupService);
        Teacher teacher = teacherService.findTeacherByIdAndPosition(dto.teacherId(), Position.TEACHER);
        Teacher assistant = teacherService.findTeacherByIdAndPosition(dto.assistantId(), Position.ASSISTANT);
        if (groupRepo.existsByTeacherId(dto.teacherId())) {
            throw new RuntimeException("Teacher already exists in group");
        }
        if (groupRepo.existsByAssistantId(dto.assistantId())) {
            throw new RuntimeException("Assistant already exists in group");
        }
        groupRepo.save(group);
        return GroupMapper.INSTANCE.groupToGroupDto(group);
    }

    @Override
    public GroupDto getGroup(Long id) {
        Group group = groupRepo.getGroupByIdAndActive(id, true);
        return GroupMapper.INSTANCE.groupToGroupDto(group);
    }

    @Override
    public List<GroupDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Group> groups = groupRepo.findAllByActiveTrue(pageable);
        return GroupMapper.INSTANCE.groupToGroupDtoList(groups);
    }

    @Override
    public GroupDto updateGroup(Long id, GroupUpdateDto uDTO) {
        Group group = groupRepo.findById(id).orElseThrow(() -> new RuntimeException("Group not found!"));
        Teacher teacher = teacherService.findTeacherByIdAndPosition(uDTO.teacherId(), Position.TEACHER);
        Teacher assistant = teacherService.findTeacherByIdAndPosition(uDTO.assistantId(), Position.ASSISTANT);
        if (!group.getTeacher().getId().equals(uDTO.teacherId()) && groupRepo.existsByTeacherId(uDTO.teacherId())) {
            throw new RuntimeException("Teacher already exists in group");
        }
        if (!group.getAssistant().getId().equals(uDTO.assistantId()) && groupRepo.existsByAssistantId(uDTO.assistantId())) {
            throw new RuntimeException("Assistant already exists in group");
        }
        GroupMapper.INSTANCE.updateGroupFromDto(uDTO, group, teacherService, ageGroupService);
        return GroupMapper.INSTANCE.groupToGroupDto(group);
    }

    @Override
    public boolean deleteGroup(Long id) {
        Group group = groupRepo.findById(id).orElseThrow(() -> new RuntimeException("Group not found!"));
        group.setActive(false);
        groupRepo.save(group);
        return true;
    }

    @Override
    public Group findById(Long id) {
        return groupRepo.findById(id).orElseThrow(() -> new RuntimeException("Group not found!"));
    }


}
