package kg.mega.kindergarten.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record ChildChangeDetail(
        String firstName,
        String lastName,
        @JsonFormat(pattern = "dd.MM.yyyy")
        Date dateOfBirth
) {
}
