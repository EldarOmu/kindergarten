package kg.mega.kindergarten.repositories;

import kg.mega.kindergarten.models.Parent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {
    Parent getParentByIdAndActive(Long id, boolean active);

    List<Parent> getAllByActiveTrue(Pageable pageable);
}
