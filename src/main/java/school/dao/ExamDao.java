package school.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import school.entity.Course;
import school.entity.Exam;
 
@Repository
public interface ExamDao extends JpaRepository<Exam, Long> {

}
