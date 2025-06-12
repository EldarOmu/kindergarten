package kg.mega.kindergarten.mappers;

import kg.mega.kindergarten.models.Contact;
import kg.mega.kindergarten.models.dtos.ContactCreateDto;
import kg.mega.kindergarten.models.dtos.ContactDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact contactCreateDtoToContact(ContactCreateDto contactCreateDto);

    @Mapping(source = "id", target = "contactId")
    ContactDto contactToContactDto(Contact contact);

    @InheritInverseConfiguration
    Contact contactDtoToContact(ContactDto contactDto);

    void updateContactFromDto(ContactDto contactDto, @MappingTarget Contact contact);
}
