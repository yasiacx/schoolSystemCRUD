package school.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.entity.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
 }
