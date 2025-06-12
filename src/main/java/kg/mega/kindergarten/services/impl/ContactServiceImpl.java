package kg.mega.kindergarten.services.impl;

import kg.mega.kindergarten.mappers.ContactMapper;
import kg.mega.kindergarten.models.Contact;
import kg.mega.kindergarten.models.dtos.ContactCreateDto;
import kg.mega.kindergarten.models.dtos.ContactDto;
import kg.mega.kindergarten.repositories.ContactRepo;
import kg.mega.kindergarten.services.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepo contactRepo;

    public ContactServiceImpl(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepo.save(contact);
    }

    @Override
    public Contact findById(Long contactId) {
        return contactRepo.findById(contactId).orElseThrow( () -> new RuntimeException("Contact not found"));
    }

    @Override
    public Contact updateContactFromDto(ContactDto contactDto) {

        if (contactDto.contactId() == null) {
            throw new RuntimeException("Contact ID is required for update.");
        }

        Contact contact = contactRepo.findById(contactDto.contactId()).orElseThrow( () -> new RuntimeException("Contact not found"));
        ContactMapper.INSTANCE.updateContactFromDto(contactDto, contact);
        return contactRepo.save(contact);
    }
}
