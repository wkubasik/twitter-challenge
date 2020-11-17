package com.kubasik.wieslaw.twitterchallenge.adapter.web.handler;

import com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.ErrorDto;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.CurrentDateTime;
import com.kubasik.wieslaw.twitterchallenge.exceptions.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final CurrentDateTime currentDateTime;

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDto> notFound(RuntimeException exception) {
        log.info(exception.getMessage(), exception);
        return new ResponseEntity<>(getErrorDto(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> serverError(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(getErrorDto(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDto getErrorDto(RuntimeException exception) {
        return new ErrorDto(exception.getMessage(), currentDateTime.now());
    }
}
