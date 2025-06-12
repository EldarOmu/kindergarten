package kg.mega.kindergarten.repositories;

import kg.mega.kindergarten.models.Teacher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Teacher getTeacherByIdAndActive(Long id, boolean active);

    List<Teacher> findAllByActiveTrue(Pageable pageable);
}
