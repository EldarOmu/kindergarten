package kg.mega.kindergarten.repositories;

import kg.mega.kindergarten.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> getAllByChildIdAndActive(Long childId, boolean active);
}
