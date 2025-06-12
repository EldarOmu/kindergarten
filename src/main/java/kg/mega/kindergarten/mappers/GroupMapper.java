package kg.mega.kindergarten.mappers;

import kg.mega.kindergarten.models.Group;
import kg.mega.kindergarten.models.dtos.GroupCreateDto;
import kg.mega.kindergarten.models.dtos.GroupDto;
import kg.mega.kindergarten.models.dtos.GroupUpdateDto;
import kg.mega.kindergarten.services.AgeGroupService;
import kg.mega.kindergarten.services.TeacherService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {TeacherMapper.class, AgeGroupMapper.class})
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(target = "teacher", expression = "java(teacherService.findById(dto.teacherId()))")
    @Mapping(target = "ageGroup", expression = "java(ageGroupService.findById(dto.ageGroupId()))")
    Group groupCreateDtoToGroup(GroupCreateDto dto, TeacherService teacherService, AgeGroupService ageGroupService);

    @Mapping(source = "id", target = "groupId")
    @Mapping(source = "teacher", target = "teacherDto")
    @Mapping(source = "ageGroup", target = "ageGroupDto")
    GroupDto groupToGroupDto(Group group);

    @Mapping(source = "id", target = "groupId")
    @Mapping(source = "teacher", target = "teacherDto")
    @Mapping(source = "ageGroup", target = "ageGroupDto")
    List<GroupDto> groupToGroupDtoList(List<Group> groups);

    void updateGroupFromDto(GroupUpdateDto uDTO, @MappingTarget Group group);
}
