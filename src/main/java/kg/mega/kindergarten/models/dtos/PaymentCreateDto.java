package kg.mega.kindergarten.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.kindergarten.enums.PaymentType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PaymentCreateDto(
        Long childId,
        double paymentSum,
        PaymentType paymentType,
        int period
) {
}
