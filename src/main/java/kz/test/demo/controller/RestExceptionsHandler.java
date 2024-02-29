package kz.test.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import kz.test.demo.domain.model.exception.ApiException;
import kz.test.demo.domain.model.exception.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackages = {"kz.test.demo.controller"})
@Slf4j
public class RestExceptionsHandler {
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApiException.class)
    public ErrorDetails handleCustom(HttpServletRequest request, ApiException ex) {
        log.warn("BaseException.class: {}", ex.toString());
        log.warn("Исключение при вызове метода: " + request.getRequestURI() + ". " + ex.getMessage(), ex);
        return ErrorDetails.builder()
                .errorCode(ex.getExceptionCode())
                .message(ex.getMessage())
                .url(request.getRequestURI())
                .build();
    }
}



