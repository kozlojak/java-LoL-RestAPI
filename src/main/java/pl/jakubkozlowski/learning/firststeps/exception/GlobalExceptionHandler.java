package pl.jakubkozlowski.learning.firststeps.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ChampionException.class})
    protected ResponseEntity<?> handleConflictOf(ChampionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
