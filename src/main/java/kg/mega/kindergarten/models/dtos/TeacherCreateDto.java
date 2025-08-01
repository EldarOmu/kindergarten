package kg.mega.kindergarten.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.kindergarten.enums.Position;
import kg.mega.kindergarten.models.Contact;

import java.util.Date;

public record TeacherCreateDto(
        String firstName,
        String lastName,
        Position position,
        @JsonFormat(pattern = "dd.MM.yyyy")
        Date dateOfBirth,
        ContactCreateDto contactCreateDto
) {
}
