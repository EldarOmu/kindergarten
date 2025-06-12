package kg.mega.kindergarten.services;

import kg.mega.kindergarten.models.Contact;
import kg.mega.kindergarten.models.dtos.ContactCreateDto;
import kg.mega.kindergarten.models.dtos.ContactDto;
import kg.mega.kindergarten.models.dtos.TeacherCreateDto;
import kg.mega.kindergarten.models.dtos.TeacherDto;

public interface ContactService {
    Contact save(Contact contact);

    Contact findById(Long contactId);

    Contact updateContactFromDto(ContactDto contactDto);
}
