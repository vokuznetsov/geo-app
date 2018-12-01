package ru.one.factor.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.one.factor.exception.dto.ErrorDto;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> handler(Exception ex) {
        // AnnotationUtils is a Spring Framework utility class.
        ResponseStatus status = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        HttpStatus code = status != null ? status.code() : HttpStatus.BAD_REQUEST;

        ErrorDto error = new ErrorDto(code.value(), ex.getMessage());

        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(error, code);
    }
}
