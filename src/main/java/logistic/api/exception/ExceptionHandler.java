package logistic.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<InvalidField> fields;

        fields = ex.getBindingResult().getAllErrors().stream().map((error -> {
            var field = ((FieldError) error).getField();
            var message = error.getDefaultMessage();
            return new InvalidField(field, message);
        })).toList();

        ArgumentNotValid exception = new ArgumentNotValid(status.value(), LocalDateTime.now(), fields);
        return handleExceptionInternal(ex, exception, headers, status, request);
    }
}
