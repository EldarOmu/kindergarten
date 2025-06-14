package kg.mega.kindergarten.repositories;

import kg.mega.kindergarten.models.Group;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {
    Group getGroupByIdAndActive(Long id, boolean active);

    List<Group> findAllByActiveTrue(Pageable pageable);

    boolean existsByTeacherId(Long teacherId);

    boolean existsByAssistantId(Long assistantId);
}
