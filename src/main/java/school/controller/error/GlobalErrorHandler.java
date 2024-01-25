package school.controller.error;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;  

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

	
	
	 @ExceptionHandler(NoSuchElementException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<Map<String, String>> handleNoSuchElementException(NoSuchElementException ex) {
	        log.error("NoSuchElementException: {}", ex.getMessage());

	        Map<String, String> response = new HashMap<>();
	        response.put("message", ex.toString());

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
}
