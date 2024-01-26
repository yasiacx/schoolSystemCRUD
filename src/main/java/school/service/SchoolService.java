package school.service;

 
 

  import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import school.controller.model.StudentCourse;
import school.controller.model.StudentData;
import school.controller.model.StudentExam;
import school.dao.CourseDao;
import school.dao.ExamDao;
import school.dao.StudentDao;
import school.entity.Course;
import school.entity.Exam;
import school.entity.Student;

 

@Service
public class SchoolService {

	@Autowired 
	private StudentDao studentDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private ExamDao examDao;
	
	
	 
	
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
			sd.getExams().clear();
			
			 

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
	  
	  
	  /* COURSE */

	  @Transactional()
	    public StudentCourse saveCourse(Long studentId, StudentCourse studentCourse) {
		  
		  Student student = studentDao.findById(studentId)
		            .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + studentId));
	        

	        Long courseId = studentCourse.getCourseId();

	        Course course = findOrCreateCourse(studentId, courseId);

	        
	        copyCourseFields(course, studentCourse);

	        
	        
 	        if(student.getCourses() == null) {
 	        	student.setCourses( new HashSet<>());
 	        }
 	       student.getCourses().add(course);
	        
	        if (course.getStudents() == null) {
	            course.setStudents(new HashSet<>());
	            course.getStudents().add(student);
	        }
	        
	        Set<Course> courses = student.getCourses();

	        courses.add(course);
	        student.setCourses(courses);

	   
	        return new StudentCourse(course);	        
	        
	    }

	  
	    private Course findOrCreateCourse(Long studentId, Long courseId) {
	    	
	        if (courseId == null) {
	            return new Course();
	        }
	        if (studentId == null) {
	            return new Course();
	        } else {
	            return findCourseById(studentId, courseId);
	        }
	    }

	    private Course findCourseById(Long studentId, Long courseId) {
	        
	    	List<Student> students = studentDao.findAll();
	    	
	    	

	    	for( Student student : students) {
	    		
		    	if(student.getCourses() != null) {
		    		
			    	for( Course course : student.getCourses() ) {
			    		
			    		if(course.getCourseId() == courseId) {
			
			    			return course;
			    		}
			    		
			    	}
		    	}

	    	}
	    	
	    	throw new IllegalArgumentException("Course with ID " + courseId + " does not have Student with ID " + studentId);
	    

	      

	    }

	    private void copyCourseFields(Course course, StudentCourse studentCourse) {

	    	course.setCourseId(studentCourse.getCourseId());
	    	course.setCourseName(studentCourse.getCourseName());
	    	course.setCourseDescription(studentCourse.getCourseDescription());
	        

	    }

		private List<StudentCourse> convertCoursesIntoDto(Set<Course> allStudentCourses) {
					
					List<StudentCourse> result = new LinkedList<>();
					
					for ( Course course : allStudentCourses) {
						
						StudentCourse scr = new StudentCourse(course);
						 
						 
						 
		
						result.add(scr);
					}
					return result;
					
				}

	    
		
		public List<StudentCourse> getCoursesbyStudentId(Long studentId) {
			
			
			 Student student = studentDao.findById(studentId)
			            .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + studentId));
		        			 	
			 
			 	
		        if(student.getCourses() != null) {
		        	List<StudentCourse>courses = convertCoursesIntoDto(student.getCourses()); 
		        	return courses;
		        }
 
		        
		        
			return null;
		}

		public StudentExam saveExam(Long studentId,Long courseId, StudentExam studentExam) {
			
			  Student student = studentDao.findById(studentId)
		            .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + studentId));
	        
			  
			  Course course = courseDao.findById(courseId)
			            .orElseThrow(() -> new NoSuchElementException("course not found with ID: " + courseId));
		        

			  Exam exam = findOrCreateExams(studentExam.getExamId(),student);

			  
 			  
			  copyStudentExamToExam(exam,studentExam);
			  
			  if(exam.getStudent() == null) {
				  exam.setStudent(new Student() );
			  }
			  if(exam.getCourse() == null) {
				  exam.setCourse(new Course());
			  }
			  exam.setStudent(student); 
			  exam.setCourse(course);
			  
			  
			  if( student.getExams() != null ) {
				  student.getExams().add(exam);
			  }
			  else {
				  student.setExams(new HashSet<>());
				  student.getExams().add(exam);
			  }
			  
			  if( exam.getStudent() == null) {
				  exam.setStudent(student);
			  }
			  
			  if (exam.getStudent().getExams() == null) {
				  exam.getStudent().setExams(new HashSet<>());
				  
			  }
			  
			  exam.getStudent().getExams().add(exam);
			  
			  
			  studentDao.save(student);
			 
			  
			return studentExam;
			
		}

		

	    private Exam findOrCreateExams(Long examId,Student student) {
	    	
	    	if(examId == null) {
	    		return new Exam();
	    	}
	        if (student.getStudentId() == null) {
	            return new Exam();
	        }
	        else {
	            return findExamById(examId,student);
	        }
	    }

	    private Exam findExamById( Long examId,Student student) {
	        
 	    	
	    	Set<Exam> exams = student.getExams();
	    	
	    	

	    	for( Exam exam : exams) {
	    		
		    		if(exam.getExamId() == examId) {
		    			return exam;

		    		}    	 


	    	}
	    	
	    	throw new IllegalArgumentException("exam with ID " + examId + " does not have Student with ID " + student.getStudentId());
	    

	    	

	    	

	    }

	
		private void copyStudentExamToExam(Exam exam, StudentExam studentExam) {
    		
			exam.setExamId	(	studentExam.getExamId() 		);
			exam.setScore   ( 	studentExam.getScore() 			);
			
     		
    	}

		

		
		public List<StudentExam> getExamsByStudentId(Long studentId) {
			
			
			 Student student = studentDao.findById(studentId)
			            .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + studentId));
		        			 	
			 
			 	
		        if(student.getExams() != null) {
		        	List<StudentExam> exams = convertExamsIntoDto(student.getExams()); 
		        	return exams;
		        }
 
		        
		        
			return null;
		}

		
		private List<StudentExam> convertExamsIntoDto(Set<Exam> allExams) {
			
			List<StudentExam> result = new LinkedList<>();
			
			for ( Exam exam : allExams) {
				
				StudentExam se = new StudentExam(exam);
				 
				  

				result.add(se);
			}
			return result;
			
		}

		 


	
}

