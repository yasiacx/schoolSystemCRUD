package school.controller.model;
 
import lombok.Data;
import lombok.NoArgsConstructor;
import school.entity.Course;
 

@Data
@NoArgsConstructor
public class StudentCourse {
	
	 private Long courseId;
	 private String courseName;
	 private String courseDescription;
 
    public StudentCourse(Course course) {
    	
        this.courseId = course.getCourseId();
        this.courseName = course.getCourseName();
        this.courseDescription = course.getCourseDescription();


    }
    
}
