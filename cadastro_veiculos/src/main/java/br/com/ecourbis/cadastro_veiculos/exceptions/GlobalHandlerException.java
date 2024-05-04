package br.com.ecourbis.cadastro_veiculos.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandlerException {
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
//        String mensagemErro = "Erro durante a execução: " + e.getMessage();
//        return new ResponseEntity<>(mensagemErro, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegratityException(DataIntegrityViolationException e) {
        String mensagemErro = "Erro de integridade de dados: ";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e.getMessage().contains("uk_placa_veiculo")) {
            mensagemErro += "Não é possível inserir uma placa já existente.";
            status = HttpStatus.CONFLICT; // Código 409 - Conflito
        } else if (e.getMessage().contains("uk_renavam_veiculo")) {
            mensagemErro += "Não é possível inserir um RENAVAM já existente.";
            status = HttpStatus.CONFLICT; // Código 409 - Conflito
        } else {
            mensagemErro +=  e.getMessage();
        }
        return new ResponseEntity<>(mensagemErro, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        String mensagemErro = "Entidade não encontrada: " + e.getMessage();
        return new ResponseEntity<>(mensagemErro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // Manipula exceções do tipo MethodArgumentNotValidException
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        // Obtém as mensagens de erro de validação e as concatena em uma única string
        String mensagemErro = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage) // Mapeia cada erro de campo para sua mensagem de erro
                .collect(Collectors.joining(", ")); // Coleta todas as mensagens de erro em uma única string separada por vírgulas

        // Retorna uma resposta com status HTTP 400 (Bad Request) e as mensagens de erro de validação no corpo da resposta
        return ResponseEntity.badRequest().body(mensagemErro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        //
        String mensagemErro = "Erro ao processar a requisição: " + e.getMessage().substring(e.getMessage().indexOf("problem: ")+9);
        return new ResponseEntity<>(mensagemErro, HttpStatus.BAD_REQUEST);
    }
}
