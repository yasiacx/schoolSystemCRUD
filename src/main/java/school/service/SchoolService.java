package school.service;

 
 

  import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
 
 import school.controller.model.StudentData;
 import school.dao.CourseDao;
import school.dao.StudentDao;
 import school.entity.Student;

 

@Service
public class SchoolService {

	@Autowired 
	private StudentDao studentDao;
	
	@Autowired
	private CourseDao courseDao;
	
	
	 
	
	@Transactional
	public StudentData saveStudentData(StudentData studentData) {
		
 

 	        Student student = findOrCreateStudent(studentData.getStudentId());
 	        
	        copyStudentFields(student , studentData);
	        
	        Student savedStudent = studentDao.save(student);
	        
	        return new StudentData(savedStudent);
	    }

	    private Student findOrCreateStudent(Long studentId) {
	    	
	        if (studentId != null) {
	        	
	            return findStudenteById(studentId);
	            
	        } else {
	        	
	            return new Student();
	        }
	    }

	    private Student findStudenteById(Long studentId) {
	    	
	        Optional<Student> optionalStudent = studentDao.findById(studentId);
 	        return optionalStudent.orElseThrow(() -> new NoSuchElementException("No such student found with the ID: " + studentId));
	        
	    }
	    
	private void copyStudentFields(Student student, StudentData studentData) {
		
		student.setStudentId		(	studentData.getStudentId() 		);
		student.setName				( 	studentData.getName() 		);
 				
		
	}

    @Transactional(readOnly = true)
    public List<StudentData> retrieveAllStudents() {
    	
    	List<Student> allStudents = studentDao.findAll();
    	
    	
		return convertStudentsIntoDto(allStudents);
	}	

	private List<StudentData> convertStudentsIntoDto(List<Student> allStudents) {
		
		List<StudentData> result = new LinkedList<>();
		
		for ( Student student : allStudents) {
			
			StudentData sd = new StudentData(student);
			 
			
			sd.getCourses().clear();
			sd.getGrades().clear();
			
			 

			result.add(sd);
		}
		return result;
		
	}

	 @Transactional(readOnly = true)
	    public StudentData getStudentById(Long studentId) {
		 Student student = findStudentById(studentId);
	        return new StudentData(student);  
	    }

	 
	 @Transactional
	    public void deleteStudentById(Long studentId) {
	        Student student = findStudentById(studentId);
	        studentDao.delete(student);
	    }


	  private Student findStudentById(Long studentId) {
	    	
	        Optional<Student> optionalStudent = studentDao.findById(studentId);
	        return optionalStudent.orElseThrow(() -> new NoSuchElementException("No such student found with the ID: " + studentId));
	        
	    }

	
	
}

