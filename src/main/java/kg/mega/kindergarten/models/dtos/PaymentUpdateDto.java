package kg.mega.kindergarten.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.kindergarten.enums.PaymentType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PaymentUpdateDto(
        ChildDto childDto,
        @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
        LocalDateTime paymentDate,
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate endPaymentDate,
        PaymentType paymentType,
        int period,
        boolean active
) {
}
