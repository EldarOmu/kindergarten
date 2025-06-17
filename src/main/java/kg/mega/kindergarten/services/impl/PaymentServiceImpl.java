package kg.mega.kindergarten.services.impl;

import kg.mega.kindergarten.mappers.PaymentMapper;
import kg.mega.kindergarten.models.ChildGroupHistory;
import kg.mega.kindergarten.models.Payment;
import kg.mega.kindergarten.models.dtos.PaymentCreateDto;
import kg.mega.kindergarten.models.dtos.PaymentDto;
import kg.mega.kindergarten.repositories.PaymentRepo;
import kg.mega.kindergarten.services.ChildGroupHistoryService;
import kg.mega.kindergarten.services.ChildService;
import kg.mega.kindergarten.services.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;
    private final ChildService childService;
    private final ChildGroupHistoryService childGroupHistoryService;

    public PaymentServiceImpl(PaymentRepo paymentRepo, ChildService childService, ChildGroupHistoryService childGroupHistoryService) {
        this.paymentRepo = paymentRepo;
        this.childService = childService;
        this.childGroupHistoryService = childGroupHistoryService;
    }

    @Override
    public PaymentDto createPayment(PaymentCreateDto paymentCreateDto) {
        Payment payment = PaymentMapper.INSTANCE.paymentCreateDtoToPayment(paymentCreateDto, childService);
        if (!payment.getChild().getGroup().isActive()) {
            throw new RuntimeException("Payments failed.");
        }
        payment.setPaymentDate(LocalDateTime.now());
        childGroupHistoryService.childDebtDeductionPayment(payment.getPaymentSum(), payment.getChild().getId());
        return PaymentMapper.INSTANCE.paymentToPaymentDto(payment);
    }

    @Override
    public List<PaymentDto> getPaymentByChildId(Long childId) {
        List<Payment> payments = paymentRepo.getAllByChildIdAndActive(childId, true);
        return PaymentMapper.INSTANCE.paymentsToPaymentDtos(payments);
    }

    @Override
    public List<PaymentDto> getPaymentsByPeriod(LocalDate startDate, LocalDate endDate, Long childId) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        List<Payment> payments = paymentRepo.getPaymentsByPeriod(startDateTime, endDateTime, childId);
        return PaymentMapper.INSTANCE.paymentsToPaymentDtos(payments);
    }

    @Override
    public boolean deletePayment(Long paymentId) {
        Payment payment = paymentRepo.getPaymentByIdAndActive(paymentId, true);
        payment.setActive(false);
        paymentRepo.save(payment);
        return true;
    }
}
