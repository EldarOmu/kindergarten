package kg.mega.kindergarten.mappers;

import kg.mega.kindergarten.models.Payment;
import kg.mega.kindergarten.models.dtos.ParentDto;
import kg.mega.kindergarten.models.dtos.PaymentCreateDto;
import kg.mega.kindergarten.models.dtos.PaymentDto;
import kg.mega.kindergarten.services.ChildService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ChildMapper.class)
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(target = "child", expression = "java(childService.findById(paymentCreateDto.childId()))")
    Payment paymentCreateDtoToPayment(PaymentCreateDto paymentCreateDto, @Context ChildService childService);

    @Mapping(source = "id", target = "paymentId")
    @Mapping(source = "child", target = "childDto")
    PaymentDto paymentToPaymentDto(Payment payment);

    @Mapping(source = "id", target = "paymentId")
    @Mapping(source = "child", target = "childDto")
    List<PaymentDto> paymentsToPaymentDtos(List<Payment> payments);
}
