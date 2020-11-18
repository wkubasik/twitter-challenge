package com.kubasik.wieslaw.twitterchallenge.adapter.web.handler;

import com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.ErrorDto;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.CurrentDateTime;
import com.kubasik.wieslaw.twitterchallenge.exceptions.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final CurrentDateTime currentDateTime;

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDto> notFound(RuntimeException exception) {
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(getErrorDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> badRequestConstraint(ConstraintViolationException exception) {
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(getErrorDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> badRequestMethodArgument(MethodArgumentNotValidException exception) {
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(getErrorDto(exception.getBindingResult().toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> serverError(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(getErrorDto(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDto getErrorDto(String message) {
        return new ErrorDto(message, currentDateTime.now());
    }
}
