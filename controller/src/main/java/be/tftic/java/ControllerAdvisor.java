package be.tftic.java;


import be.tftic.java.bll.exceptions.EntityNotFoundException;
import be.tftic.java.bll.exceptions.complaint.CloseComplaintException;
import be.tftic.java.bll.exceptions.user.UserDeniedAccessException;
import be.tftic.java.bll.exceptions.user.UserEmailAlreadyExistException;
import be.tftic.java.bll.exceptions.user.UserException;
import be.tftic.java.bll.exceptions.user.UserPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage()); //406
    }

    @ExceptionHandler(UserDeniedAccessException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserDeniedAccessException e) {
        log.warn(e.toString());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage()); // 403
    }

    @ExceptionHandler({UserPasswordException.class})
    public ResponseEntity<String> handleUserEmailAlreadyExistException(UserPasswordException e) {
        log.warn(e.toString());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage()); //406
    }

    @ExceptionHandler({CloseComplaintException.class})
    public ResponseEntity<String> handleUserEmailAlreadyExistException(CloseComplaintException e) {
        log.warn(e.toString());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage()); //406
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(EntityNotFoundException e) {
        log.warn(e.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors =
                e.getBindingResult().getAllErrors()
                        .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .distinct().toList();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors); // 406
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(e.toString());
        return ResponseEntity.badRequest().body(e); // 400
    }

}

