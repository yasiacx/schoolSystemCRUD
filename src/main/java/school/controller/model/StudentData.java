package school.controller.model;

import java.util.HashSet;
import java.util.Set;
 
import lombok.Data;
import lombok.NoArgsConstructor;
import school.entity.Course;
import school.entity.Exam;
import school.entity.Grade;
import school.entity.Student;

@Data
@NoArgsConstructor
public class StudentData {

    private Long studentId;
    private String name;
    
    private Set<StudentGrade> grades;
    private Set<StudentCourse> courses;  
    private Set<Exam> exams;  

    
    public StudentData(Student student) {
        this.studentId = student.getStudentId();
        this.name = student.getName();

        this.grades = new HashSet<>();
        if (student.getGrades() != null) {
            for (Grade grade : student.getGrades()) {
                this.grades.add(new StudentGrade(grade));
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
