package kg.mega.kindergarten.services.impl;

import jakarta.transaction.Transactional;
import kg.mega.kindergarten.enums.Position;
import kg.mega.kindergarten.mappers.ContactMapper;
import kg.mega.kindergarten.mappers.TeacherMapper;
import kg.mega.kindergarten.models.Contact;
import kg.mega.kindergarten.models.Teacher;
import kg.mega.kindergarten.models.dtos.TeacherCreateDto;
import kg.mega.kindergarten.models.dtos.TeacherDto;
import kg.mega.kindergarten.models.dtos.TeacherUpdateDto;
import kg.mega.kindergarten.repositories.TeacherRepo;
import kg.mega.kindergarten.services.ContactService;
import kg.mega.kindergarten.services.TeacherService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepo teacherRepo;
    private final ContactService contactService;

    public TeacherServiceImpl(TeacherRepo teacherRepo, ContactService contactService) {
        this.teacherRepo = teacherRepo;
        this.contactService = contactService;
    }

    @Override
    public TeacherDto createTeacher(TeacherCreateDto teacherCreateDto) {
        Teacher teacher = TeacherMapper.INSTANCE.teacherCreateDtoToTeacher(teacherCreateDto);
        Contact contact = contactService.save(teacher.getContact());
        teacher.setContact(contact);
        teacherRepo.save(teacher);
        return TeacherMapper.INSTANCE.teacherToTeacherDto(teacher);
    }

    @Override
    public TeacherDto getTeacher(Long id) {
        Teacher teacher = teacherRepo.getTeacherByIdAndActive(id, true);
        return TeacherMapper.INSTANCE.teacherToTeacherDto(teacher);
    }

    @Override
    public List<TeacherDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Teacher> teachers = teacherRepo.findAllByActiveTrue(pageable);
        return TeacherMapper.INSTANCE.teachersToTeachersDto(teachers);
    }

    @Override
    public TeacherDto update(Long id, TeacherUpdateDto uDTO) {
        Teacher teacher = teacherRepo.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found!"));
        TeacherMapper.INSTANCE.updateTeacherFromDto(uDTO, teacher);
        Contact contact = contactService.updateContactFromDto(uDTO.contactDto());
        teacher.setContact(contact);
        teacherRepo.save(teacher);
        return TeacherMapper.INSTANCE.teacherToTeacherDto(teacher);
    }

    @Override
    public boolean delete(Long id) {
        Teacher teacher = teacherRepo.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found!"));
        teacher.setActive(false);
        teacherRepo.save(teacher);
        return true;
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepo.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found!"));
    }

    @Override
    public Teacher findTeacherByIdAndPosition(Long id, Position position) {
        Teacher teacher = teacherRepo.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found!"));
        if (!teacher.getPosition().equals(position)) {
            throw new RuntimeException("Teacher or assistant with id " + id + " does not have position " + position);
        }
        return teacher;
    }

}
