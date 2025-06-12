package kg.mega.kindergarten.repositories;

import kg.mega.kindergarten.models.AgeGroup;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgeGroupRepo extends JpaRepository<AgeGroup, Long> {
    AgeGroup getAgeGroupByIdAndActive(Long id, boolean active);

    List<AgeGroup> getAllByActiveTrue(Pageable pageable);
}
