package es.geeksusma.warehouse.api;

import es.geeksusma.warehouse.api.dto.ErrorDTO;
import es.geeksusma.warehouse.api.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        ex.getBindingResult().getFieldErrors().forEach(e -> response.addError(ErrorDTO.ErrorBuilder.builder().build(e.getCode(), "/" + e.getField(), e.getDefaultMessage(), e.getDefaultMessage())));
        return ResponseEntity.badRequest().body(response);
    }

}
