package be.tftic.java;


import be.tftic.java.bll.exceptions.user.UserEmailAlreadyExistException;
import be.tftic.java.bll.exceptions.user.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({UserEmailAlreadyExistException.class})
    public ResponseEntity<String> handleUserEmailAlreadyExistException(UserException e) {
        log.warn(e.toString());
        return ResponseEntity.status(406).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors =
                e.getBindingResult().getAllErrors()
                        .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .distinct().toList();
        return ResponseEntity.status(406).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(e.toString());
        return ResponseEntity.badRequest().body(e);
    }

}

