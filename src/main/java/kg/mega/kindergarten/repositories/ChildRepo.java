package kg.mega.kindergarten.repositories;

import kg.mega.kindergarten.models.Child;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepo extends JpaRepository<Child, Long> {
    Child getChildByIdAndActive(Long id, boolean active);

    List<Child> getAllByActiveTrue(Pageable pageable);
}
