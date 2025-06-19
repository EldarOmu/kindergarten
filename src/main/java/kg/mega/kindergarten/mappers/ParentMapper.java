package kg.mega.kindergarten.mappers;

import kg.mega.kindergarten.models.Parent;
import kg.mega.kindergarten.models.dtos.ParentCreateDto;
import kg.mega.kindergarten.models.dtos.ParentDetailDto;
import kg.mega.kindergarten.models.dtos.ParentDto;
import kg.mega.kindergarten.models.dtos.ParentUpdateDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ContactMapper.class)
public interface ParentMapper {
    ParentMapper INSTANCE = Mappers.getMapper(ParentMapper.class);

    @Mapping(source = "contactCreateDto", target = "contact")
    Parent parentCreateDtoToParent(ParentCreateDto dto);

    @Mapping(source = "contact", target = "contactDto")
    @Mapping(source = "id", target = "parentId")
    ParentDto parentToParentDto(Parent parent);

    @Mapping(source = "id", target = "parentId")
    List<ParentDto> parentsToParentDtos(List<Parent> parents);

    void updateParentFromDto(ParentUpdateDto dto, @MappingTarget Parent parent);

    void changeParentFromParentChangeDetailDto(Parent parent, @MappingTarget ParentDetailDto parentDetailDto);
}
