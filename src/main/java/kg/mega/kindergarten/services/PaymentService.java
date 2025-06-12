package kg.mega.kindergarten.services;

import kg.mega.kindergarten.models.dtos.PaymentCreateDto;
import kg.mega.kindergarten.models.dtos.PaymentDto;

import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(PaymentCreateDto paymentCreateDto);

    List<PaymentDto> getPaymentByChildId(Long childId);
}
