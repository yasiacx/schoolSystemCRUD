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
public class StudentExam {
    private Long examId;
    private Float score;
  
    

    
    public StudentExam(Exam exam) {
        this.examId = exam.getExamId();
        this.score = exam.getScore();
    }

   
}
