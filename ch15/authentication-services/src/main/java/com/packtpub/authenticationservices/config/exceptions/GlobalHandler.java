package com.packtpub.authenticationservices.config.exceptions;

import com.packtpub.authenticationservices.internal.exceptions.BusinessException;
import com.packtpub.authenticationservices.internal.exceptions.BusinessExceptionResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<BusinessExceptionResponse<Object>>> handleException(BusinessException e) {
        var response = new BusinessExceptionResponse<>(
                e.getCode(),
                null,
                e.getLocalizedMessage()
        );
        return Mono.just(ResponseEntity.ok(response));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Mono<ProblemDetail> handleBadCredentials(BadCredentialsException ex) {
        return Mono.just(buildProblemDetail(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex.getMessage()));
    }

    @ExceptionHandler(AccountStatusException.class)
    public Mono<ProblemDetail> handleAccountStatus(AccountStatusException ex) {
        return Mono.just(buildProblemDetail(HttpStatus.FORBIDDEN, ex.getMessage(), "The account is locked"));
    }

    @ExceptionHandler({AccessDeniedException.class, AuthorizationDeniedException.class})
    public Mono<ProblemDetail> handleAccessDenied(Exception ex) {
        return Mono.just(buildProblemDetail(HttpStatus.FORBIDDEN, ex.getMessage(), "You are not authorized to access this resource"));
    }

    @ExceptionHandler({io.jsonwebtoken.security.SignatureException.class, java.security.SignatureException.class})
    public Mono<ProblemDetail> handleInvalidSignature(Exception ex) {
        return Mono.just(buildProblemDetail(HttpStatus.UNAUTHORIZED, ex.getMessage(), "The JWT signature is invalid"));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public Mono<ProblemDetail> handleExpiredJwt(ExpiredJwtException ex) {
        return Mono.just(buildProblemDetail(HttpStatus.UNAUTHORIZED, ex.getMessage(), "The JWT has expired"));
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public Mono<ProblemDetail> handleUnauthorized(HttpClientErrorException.Unauthorized ex) {
        return Mono.just(buildProblemDetail(HttpStatus.UNAUTHORIZED, ex.getMessage(), "The JWT has expired"));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ProblemDetail> handleGeneral(Exception ex) {
        return Mono.just(buildProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "Unknown internal server error"));
    }

    private ProblemDetail buildProblemDetail(HttpStatus status, String message, String description) {
        var detail = ProblemDetail.forStatusAndDetail(status, message);
        detail.setProperty("description", description);
        return detail;
    }
}