package kg.mega.kindergarten.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.kindergarten.enums.Position;

import java.util.Date;

public record TeacherUpdateDto(
        String firstName,
        String lastName,
        Position position,
        @JsonFormat(pattern = "dd.MM.yyyy")
        Date dateOfBirth,
        ContactDto contactDto,
        boolean active
){
}
