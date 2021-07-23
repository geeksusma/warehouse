package es.geeksusma.warehouse.api;

import es.geeksusma.warehouse.api.dto.ErrorDTO;
import es.geeksusma.warehouse.api.dto.ErrorResponse;
import es.geeksusma.warehouse.item.SerialNumberDuplicityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        ex.getBindingResult().getFieldErrors().forEach(e -> response.addError(ErrorDTO.ErrorBuilder.builder().build(e.getCode(), "/" + e.getField(), e.getDefaultMessage(), e.getDefaultMessage())));
        return ResponseEntity.badRequest().body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SerialNumberDuplicityException.class)
    public ResponseEntity<ErrorResponse> handleSerialNumberDuplicated(SerialNumberDuplicityException e) {
        return ResponseEntity.badRequest().body(createTakenSerialNumberError(e));
    }

    private ErrorResponse createTakenSerialNumberError(SerialNumberDuplicityException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorDTO takenSerialNumberError = ErrorDTO.ErrorBuilder.builder().build("001", "/serialNumber", "The serial number is taken", e.getMessage());
        errorResponse.addError(takenSerialNumberError);
        return errorResponse;
    }


}
