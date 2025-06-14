package kg.mega.kindergarten.mappers;

import kg.mega.kindergarten.models.Child;
import kg.mega.kindergarten.models.dtos.ChildCreateDto;
import kg.mega.kindergarten.models.dtos.ChildDto;
import kg.mega.kindergarten.models.dtos.ChildUpdateDto;
import kg.mega.kindergarten.services.GroupService;
import kg.mega.kindergarten.services.ParentService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ParentMapper.class, GroupMapper.class})
public interface ChildMapper {
    ChildMapper INSTANCE = Mappers.getMapper(ChildMapper.class);

    @Mapping(target = "parents", expression = "java(parentService.findAllById(dto.parentIds()))")
    @Mapping(target = "group", expression = "java(groupService.findById(dto.groupId()))")
    Child childCreateDtoToChild(ChildCreateDto dto, ParentService parentService, GroupService groupService);

    @Mapping(source = "id", target = "childId")
    @Mapping(source = "group", target = "groupDto")
    @Mapping(source = "parents", target = "parentDtos")
    ChildDto childToChildDto(Child child);

    @Mapping(source = "id", target = "childId")
    @Mapping(source = "group", target = "groupDto")
    @Mapping(source = "parents", target = "parentDtos")
    List<ChildDto> childToChildDtoList(List<Child> childList);

    @Mapping(target = "parents", expression = "java(parentService.findAllById(uDTO.parentIds()))")
    @Mapping(target = "group", expression = "java(groupService.findById(uDTO.groupId()))")
    void updateChildFromDto(ChildUpdateDto uDTO, @MappingTarget Child child, ParentService parentService, GroupService groupService);
}
