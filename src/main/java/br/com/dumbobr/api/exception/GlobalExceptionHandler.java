package br.com.dumbobr.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponse> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {
        List<String> mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .toList();

        ErroValidacaoResponse response = new ErroValidacaoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                mensagens
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

        @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroValidacaoResponse> handleRuntimeException(
            RuntimeException ex
    ) {
        ErroValidacaoResponse response = new ErroValidacaoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro na requisição",
                List.of(ex.getMessage())
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}