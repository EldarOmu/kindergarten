package kg.mega.kindergarten.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.kindergarten.enums.Position;
import kg.mega.kindergarten.models.Contact;

import java.util.Date;

public record TeacherDto(
        Long teacherId,
        String firstName,
        String lastName,
        Position position,
        @JsonFormat(pattern = "dd.MM.yyyy")
        Date dateOfBirth,
        ContactDto contactDto,
        boolean active
) {
}
