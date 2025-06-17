package kg.mega.kindergarten.mappers;

import kg.mega.kindergarten.models.ChildGroupHistory;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryCreateDto;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryDto;
import kg.mega.kindergarten.services.ChildService;
import kg.mega.kindergarten.services.GroupService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {GroupMapper.class, ChildMapper.class})
public interface ChildGroupHistoryMapper {
    ChildGroupHistoryMapper INSTANCE = Mappers.getMapper(ChildGroupHistoryMapper.class);

    @Mapping(target = "group", expression = "java(groupService.findById(childGroupHistoryCreateDto.groupId()))")
    @Mapping(target = "child", expression = "java(childService.findById(childGroupHistoryCreateDto.childId()))")
    ChildGroupHistory childGroupHistoryCreateDtoToChild(ChildGroupHistoryCreateDto childGroupHistoryCreateDto, GroupService groupService, ChildService childService);

    @Mapping(source = "id", target = "childGroupHistoryId")
    @Mapping(source = "group", target = "groupDto")
    @Mapping(source = "child", target = "childDto")
    ChildGroupHistoryDto childGroupHistoryToChildGroupHistoryDto(ChildGroupHistory childGroupHistory);

    @Mapping(source = "id", target = "childGroupHistoryId")
    @Mapping(source = "group", target = "groupDto")
    @Mapping(source = "child", target = "childDto")
    List<ChildGroupHistoryDto> childGroupHistoryToChildGroupHistoryDtoList(List<ChildGroupHistory> childGroupHistory);
}
