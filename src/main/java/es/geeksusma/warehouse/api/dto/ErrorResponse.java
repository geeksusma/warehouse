package es.geeksusma.warehouse.api.dto;

import java.util.LinkedList;
import java.util.List;

public class ErrorResponse {

    private List<ErrorDTO> errors;

    public ErrorResponse() {
        errors = new LinkedList<>();
    }

    public void addError(final ErrorDTO error) {
        errors.add(error);
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }
}
