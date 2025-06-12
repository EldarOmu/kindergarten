package kg.mega.kindergarten.services.impl;

import kg.mega.kindergarten.mappers.ChildMapper;
import kg.mega.kindergarten.models.Child;
import kg.mega.kindergarten.models.Parent;
import kg.mega.kindergarten.models.dtos.ChildCreateDto;
import kg.mega.kindergarten.models.dtos.ChildDto;
import kg.mega.kindergarten.models.dtos.ChildUpdateDto;
import kg.mega.kindergarten.repositories.ChildRepo;
import kg.mega.kindergarten.services.ChildService;
import kg.mega.kindergarten.services.GroupService;
import kg.mega.kindergarten.services.ParentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChildServiceImpl implements ChildService {
    private final ChildRepo childRepo;
    private final ParentService parentService;
    private final GroupService groupService;

    public ChildServiceImpl(ChildRepo childRepo, ParentService parentService, GroupService groupService) {
        this.childRepo = childRepo;
        this.parentService = parentService;
        this.groupService = groupService;
    }

    @Override
    @Transactional
    public ChildDto create(ChildCreateDto dto) {
        Child child = ChildMapper.INSTANCE.childCreateDtoToChild(dto, parentService, groupService);
        childRepo.save(child);
        return ChildMapper.INSTANCE.childToChildDto(child);
    }

    @Override
    public ChildDto getChild(Long id) {
        Child child = childRepo.getChildByIdAndActive(id, true);
        return ChildMapper.INSTANCE.childToChildDto(child);
    }

    @Override
    public List<ChildDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Child> childList = childRepo.findAll(pageable).stream().toList();
        return ChildMapper.INSTANCE.childToChildDtoList(childList);
    }

    @Override
    public ChildDto updateChild(Long id, ChildUpdateDto uDTO) {
        Child child = childRepo.findById(id).orElseThrow( () -> new RuntimeException("Child not found!"));
        ChildMapper.INSTANCE.updateChildFromDto(uDTO, child);
        return ChildMapper.INSTANCE.childToChildDto(child);
    }

    @Override
    public boolean deleteChild(Long id) {
        Child child = childRepo.findById(id).orElseThrow( () -> new RuntimeException("Child not found!"));
        child.setActive(false);
        childRepo.save(child);
        return true;
    }

    @Override
    public Child findById(Long id) {
        return childRepo.getChildByIdAndActive(id, true);
    }
}
