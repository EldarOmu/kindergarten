package kg.mega.kindergarten.services.impl;

import kg.mega.kindergarten.mappers.PaymentMapper;
import kg.mega.kindergarten.models.Payment;
import kg.mega.kindergarten.models.dtos.PaymentCreateDto;
import kg.mega.kindergarten.models.dtos.PaymentDto;
import kg.mega.kindergarten.repositories.PaymentRepo;
import kg.mega.kindergarten.services.ChildService;
import kg.mega.kindergarten.services.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;
    private final ChildService childService;

    public PaymentServiceImpl(PaymentRepo paymentRepo, ChildService childService) {
        this.paymentRepo = paymentRepo;
        this.childService = childService;
    }

    @Override
    public PaymentDto createPayment(PaymentCreateDto paymentCreateDto) {
        Payment payment = PaymentMapper.INSTANCE.paymentCreateDtoToPayment(paymentCreateDto, childService);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setEndPaymentDate(LocalDate.now().plusMonths(paymentCreateDto.period()));
        paymentRepo.save(payment);
        return PaymentMapper.INSTANCE.paymentToPaymentDto(payment);
    }

    @Override
    public List<PaymentDto> getPaymentByChildId(Long childId) {
        List<Payment> payments = paymentRepo.getAllByChildIdAndActive(childId, true);
        return PaymentMapper.INSTANCE.paymentsToPaymentDtos(payments);
    }
}
