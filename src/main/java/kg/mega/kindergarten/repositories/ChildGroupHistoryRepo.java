package kg.mega.kindergarten.repositories;

import kg.mega.kindergarten.models.ChildGroupHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildGroupHistoryRepo extends JpaRepository<ChildGroupHistory, Long> {
}
