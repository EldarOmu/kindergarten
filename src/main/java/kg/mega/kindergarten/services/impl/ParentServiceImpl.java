package kg.mega.kindergarten.services.impl;

import jakarta.transaction.Transactional;
import kg.mega.kindergarten.mappers.ContactMapper;
import kg.mega.kindergarten.mappers.ParentMapper;
import kg.mega.kindergarten.models.Contact;
import kg.mega.kindergarten.models.Parent;
import kg.mega.kindergarten.models.dtos.ParentCreateDto;
import kg.mega.kindergarten.models.dtos.ParentDto;
import kg.mega.kindergarten.models.dtos.ParentUpdateDto;
import kg.mega.kindergarten.repositories.ParentRepo;
import kg.mega.kindergarten.services.ContactService;
import kg.mega.kindergarten.services.ParentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ParentServiceImpl implements ParentService {
    private final ParentRepo parentRepo;
    private final ContactService contactService;

    public ParentServiceImpl(ParentRepo parentRepo, ContactService contactService) {
        this.parentRepo = parentRepo;
        this.contactService = contactService;
    }

    @Override
    public ParentDto create(ParentCreateDto dto) {
        Parent parent = ParentMapper.INSTANCE.parentCreateDtoToParent(dto);
        Contact contact = contactService.save(parent.getContact());
        parent.setContact(contact);
        parentRepo.save(parent);
        return ParentMapper.INSTANCE.parentToParentDto(parent);
    }

    @Override
    public ParentDto getParent(Long id) {
        Parent parent = parentRepo.getParentByIdAndActive(id, true);
        return ParentMapper.INSTANCE.parentToParentDto(parent);
    }

    @Override
    public List<ParentDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Parent> parents = parentRepo.getAllByActiveTrue(pageable);
        return ParentMapper.INSTANCE.parentsToParentDtos(parents);
    }

    @Override
    public ParentDto update(Long id, ParentUpdateDto uDTO) {
        Parent parent = parentRepo.findById(id).orElseThrow( () -> new RuntimeException("Parent not found"));
        ParentMapper.INSTANCE.updateParentFromDto(uDTO, parent);
        Contact contact = contactService.updateContactFromDto(uDTO.contactDto());
        parent.setContact(contact);
        parentRepo.save(parent);
        return ParentMapper.INSTANCE.parentToParentDto(parent);
    }

    @Override
    public boolean delete(Long id) {
        Parent parent = parentRepo.findById(id).orElseThrow( () -> new RuntimeException("Parent not found"));
        parent.setActive(false);
        parentRepo.save(parent);
        return true;
    }

    @Override
    public List<Parent> findAllById(List<Long> parentIds) {
        return parentRepo.findAllById(parentIds);
    }
}
