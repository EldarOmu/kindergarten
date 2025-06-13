package kg.mega.kindergarten.services;

import kg.mega.kindergarten.models.dtos.PaymentCreateDto;
import kg.mega.kindergarten.models.dtos.PaymentDto;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(PaymentCreateDto paymentCreateDto);

    List<PaymentDto> getPaymentByChildId(Long childId);

    List<PaymentDto> getPaymentsByPeriod(LocalDate startDate, LocalDate endDate, Long childId);

    boolean deletePayment(Long paymentId);
}
