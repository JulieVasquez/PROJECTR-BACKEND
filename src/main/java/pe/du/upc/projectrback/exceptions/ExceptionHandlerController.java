package pe.du.upc.projectrback.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageGeneric resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request){
        log.error("Recurso no encontrado: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(IncompleteDataException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessageGeneric incompleteDataExceptionHandler(IncompleteDataException ex, WebRequest request){
        log.warn("Datos incompletos: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), request);
    }

    @ExceptionHandler(KeyRepeatedDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessageGeneric keyRepeatedDataExceptionHandler(KeyRepeatedDataException ex, WebRequest request){
        log.warn("Clave repetida detectada: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageGeneric globalExceptionHandler(Exception ex, WebRequest request) {
        log.error("Error interno del servidor: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado.", request);
    }

    private ErrorMessageGeneric buildErrorResponse(HttpStatus status, String message, WebRequest request) {
        return new ErrorMessageGeneric(status.value(), message, request.getDescription(false), new Date());
    }

}
