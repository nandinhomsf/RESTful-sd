package fernando.excecao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RespostaErro> handleConstraintViolation(
            ConstraintViolationException e, HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();
        for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
            map.put(cv.getPropertyPath().toString(), cv.getMessage());
        }
        RespostaErro respostaErro = new RespostaErro(
                LocalDateTime.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                request.getMethod(),
                request.getRequestURI(),
                map,
                e.getMessage());
        return new ResponseEntity<>(respostaErro, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<RespostaErro> handleEntidadeNaoEncontrada(
            EntidadeNaoEncontradaException e, HttpServletRequest request) {
        RespostaErro respostaErro = new RespostaErro(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                request.getMethod(),
                request.getRequestURI(),
                null,
                e.getMessage());
        return new ResponseEntity<>(respostaErro, HttpStatus.NOT_FOUND);
    }
}