package br.gov.agu.abakoapi.exceptions.handler;

import br.gov.agu.abakoapi.exceptions.DossieForaDeValidade;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DossieForaDeValidade.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardError handleDossieForaDeValidade(final DossieForaDeValidade e, final HttpServletRequest request) {
        StandardError error = new StandardError();
        Integer status = HttpStatus.BAD_REQUEST.value();
        error.setStatus(status);
        error.setTimeStamp(Instant.now());
        error.setError("Dossie fora de validade");
        error.setMensagem(e.getMessage());
        error.setPath(request.getRequestURI());
        return error;
    }
}
