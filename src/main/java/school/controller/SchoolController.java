package school.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import school.controller.model.StudentData;
import school.service.SchoolService;


@RestController
@RequestMapping("/school")
@Slf4j
public class SchoolController {
	
	@Autowired
	private SchoolService schoolService;
	
    
    // STUDENT : CREATE , UPDATE ,READ , DELETE
	    @PostMapping("/student")
	    @ResponseStatus(HttpStatus.CREATED)
	    public StudentData createStudent(@RequestBody StudentData studentData) {
	        log.info("Received POST request for /student: {}", studentData);
	        return schoolService.saveStudentData(studentData);
	    }
	    
	    
	    @PutMapping("/student/{studentId}")
	    @ResponseStatus(HttpStatus.OK)
	    public StudentData updateStudent( @PathVariable Long studentId, @RequestBody StudentData studentData) {
	    	
	    	
	        log.info("Received PUT request for /student with the ID: {}", studentId );
	        
	        studentData.setStudentId(studentId); 
	        
			return schoolService.saveStudentData(studentData);
	    	
	    }
	    
	    @GetMapping("/students")
	    @ResponseStatus(HttpStatus.OK)
	    public List<StudentData> getAllStudentsData() {
	    	
			return schoolService.retrieveAllStudents();
	    	
	    }
	    
	    @GetMapping("/student/{studentId}")
	    public ResponseEntity<StudentData> getStudentById(@PathVariable Long studentId) {
	        log.info("Received GET request for /pet_store with the ID: {}", studentId);

	        StudentData studentData = schoolService.getStudentById(studentId);

	        return ResponseEntity.ok(studentData);
	    }
	    @DeleteMapping("/student/{studentId}")
	    public ResponseEntity<Map<String, String>> deleteStudentById(@PathVariable Long studentId) {
	    	
	        log.info("Received DELETE request for /student with the ID: {}", studentId);

	        schoolService.deleteStudentById(studentId);

	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Deletion successful");

	        return ResponseEntity.ok(response);
	    }
	    
	    

}
