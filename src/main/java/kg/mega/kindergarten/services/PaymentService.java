package kg.mega.kindergarten.services;

import kg.mega.kindergarten.models.dtos.PaymentCreateDto;
import kg.mega.kindergarten.models.dtos.PaymentDto;

public interface PaymentService {
    PaymentDto createPayment(PaymentCreateDto paymentCreateDto);
}
