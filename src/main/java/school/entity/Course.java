package school.entity;

 
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;
    private String courseDescription;

    @ManyToMany(mappedBy = "courses")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Student> students;

}
