package kg.mega.kindergarten.repositories;

import kg.mega.kindergarten.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> getAllByChildIdAndActive(Long childId, boolean active);

    @Query("select u from Payment u where u.paymentDate between ?1 and ?2 and u.child.id = ?3")
    List<Payment> getPaymentsByPeriod(LocalDateTime startDate, LocalDateTime endDate, Long childId);
}
