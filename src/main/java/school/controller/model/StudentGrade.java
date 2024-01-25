package school.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import school.entity.Grade;

@Data
@NoArgsConstructor
public class StudentGrade {
    private Long gradeId;
    private Integer score;
    private Long studentId;
    private Long courseId;

    public StudentGrade(Grade grade) {
        this.gradeId = grade.getGradeId();
        this.score = grade.getScore();
        this.studentId = grade.getStudent().getStudentId();
        this.courseId = grade.getCourse().getCourseId();
    }
}
