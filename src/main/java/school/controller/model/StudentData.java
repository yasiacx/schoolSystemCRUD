package school.controller.model;

import java.util.HashSet;
import java.util.Set;
 
import lombok.Data;
import lombok.NoArgsConstructor;
import school.entity.Course;
import school.entity.Exam;
import school.entity.Student;

@Data
@NoArgsConstructor
public class StudentData {

    private Long studentId;
    private String name;
    
    private Set<StudentCourse> courses;  
    private Set<StudentExam> exams;  

    
    public StudentData(Student student) {
        this.studentId = student.getStudentId();
        this.name = student.getName();

        this.exams = new HashSet<>();
        if (student.getExams() != null) {
            for (Exam exam : student.getExams()) {
                this.exams.add(new StudentExam(exam));
            }
        }

        this.courses = new HashSet<>();
        if (student.getCourses() != null) {
            for (Course course : student.getCourses()) {
                this.courses.add(new StudentCourse(course));
            }
        }
        
     
     
    }
}
