package kg.mega.kindergarten.repositories;

import kg.mega.kindergarten.models.ChildGroupHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildGroupHistoryRepo extends JpaRepository<ChildGroupHistory, Long> {

    ChildGroupHistory findChildGroupHistoriesByChildId(Long childId);

    List<ChildGroupHistory> findAllByDebtGreaterThan(double debt, Pageable pageable);
}
