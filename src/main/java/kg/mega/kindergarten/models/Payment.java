package kg.mega.kindergarten.models;

import jakarta.persistence.*;
import kg.mega.kindergarten.enums.PaymentType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "children_id")
    private Child children;
    private double paymentSum;
    private LocalDateTime paymentDate;
    private LocalDate endPaymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private int period;

    public LocalDate getEndPaymentDate() {
        return endPaymentDate;
    }

    public void setEndPaymentDate(LocalDate endPaymentDate) {
        this.endPaymentDate = endPaymentDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Child getChildren() {
        return children;
    }

    public void setChildren(Child children) {
        this.children = children;
    }

    public double getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(double paymentSum) {
        this.paymentSum = paymentSum;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
