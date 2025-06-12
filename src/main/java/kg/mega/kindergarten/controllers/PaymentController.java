package kg.mega.kindergarten.controllers;

import kg.mega.kindergarten.models.dtos.PaymentCreateDto;
import kg.mega.kindergarten.models.dtos.PaymentDto;
import kg.mega.kindergarten.models.dtos.PaymentUpdateDto;
import kg.mega.kindergarten.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody PaymentCreateDto paymentCreateDto) {
        return ResponseEntity.ok(paymentService.createPayment(paymentCreateDto));
    }

    @GetMapping("/get-payment-by-child-id/{childId}")
    public ResponseEntity<?> getPaymentByChildId(@PathVariable Long childId) {
        return ResponseEntity.ok(paymentService.getPaymentByChildId(childId));
    }
}
