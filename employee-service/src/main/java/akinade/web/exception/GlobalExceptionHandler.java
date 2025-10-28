package akinade.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final URI NOT_FOUND_TYPE = URI.create("https://api.company.com/errors/not-found");
    private static final URI CONFLICT_TYPE = URI.create("https://api.company.com/errors/conflict");
    private static final URI BAD_REQUEST_TYPE = URI.create("https://api.company.com/errors/bad-request");
    private static final URI SERVER_ERROR_TYPE = URI.create("https://api.company.com/errors/server-error");

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problem.setTitle("Internal Server Error");
        problem.setType(SERVER_ERROR_TYPE);
        problem.setProperty("timestamp", LocalDateTime.now().format(FORMATTER));
        return problem;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Resource Not Found");
        problem.setType(NOT_FOUND_TYPE);
        problem.setProperty("timestamp", LocalDateTime.now().format(FORMATTER));
        return problem;
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ProblemDetail handleDuplicateResource(DuplicateResourceException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("Duplicate Resource");
        problem.setType(CONFLICT_TYPE);
        problem.setProperty("timestamp", LocalDateTime.now().format(FORMATTER));
        return problem;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        problem.setTitle("Invalid Input Data");
        problem.setType(BAD_REQUEST_TYPE);

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

        problem.setProperty("errors", fieldErrors);
        problem.setProperty("timestamp", LocalDateTime.now().format(FORMATTER));
        return problem;
    }
}
