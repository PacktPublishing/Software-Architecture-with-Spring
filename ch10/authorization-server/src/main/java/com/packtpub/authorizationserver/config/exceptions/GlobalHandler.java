package com.packtpub.authorizationserver.config.exceptions;

import com.packtpub.authorizationserver.internal.exceptions.BusinessException;
import com.packtpub.authorizationserver.internal.exceptions.BusinessExceptionResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.SignatureException;

@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessExceptionResponse<Object>> handleException(BusinessException e){
        BusinessExceptionResponse<Object>response = new BusinessExceptionResponse<>(
                e.getCode(),
                null,
                e.getLocalizedMessage()
        );

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail;

        return switch (exception) {
            case BadCredentialsException ex -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
                errorDetail.setProperty("description", "The username or password is incorrect");
                yield errorDetail;
            }
            case AccountStatusException ex -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
                errorDetail.setProperty("description", "The account is locked");
                yield errorDetail;
            }
            case AccessDeniedException ex -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
                errorDetail.setProperty("description", "You are not authorized to access this resource");
                yield errorDetail;
            }
            case SignatureException ex -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
                errorDetail.setProperty("description", "The JWT signature is invalid");
                yield errorDetail;
            }
            default -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
                errorDetail.setProperty("description", "Unknown internal server error.");
                yield errorDetail;
            }
        };

    }
}
