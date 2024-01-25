package school.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import school.entity.Course;
 
@Repository
public interface CourseDao extends JpaRepository<Course, Long> {

}
