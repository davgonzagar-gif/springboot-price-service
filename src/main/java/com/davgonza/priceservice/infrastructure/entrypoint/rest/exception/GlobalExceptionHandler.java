package com.davgonza.priceservice.infrastructure.entrypoint.rest.exception;

import com.davgonza.priceservice.domain.exception.PriceNotFoundException;
import com.davgonza.priceservice.infrastructure.entrypoint.rest.mapper.ErrorResponseMapper;
import com.davgonza.priceservice.infrastructure.entrypoint.rest.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorResponseMapper errorResponseMapper;

    @ExceptionHandler(
            PriceNotFoundException.class
    )
    public ResponseEntity<ErrorResponse> handlePriceNotFound(
            PriceNotFoundException exception,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        errorResponseMapper.build(
                                HttpStatus.NOT_FOUND,
                                exception.getMessage(),
                                request
                        )
                );
    }

    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception exception,
            HttpServletRequest request) {

        return ResponseEntity.status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(
                        errorResponseMapper.build(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Unexpected server error",
                                request
                        )
                );
    }
    @ExceptionHandler({
            jakarta.validation.ConstraintViolationException.class,
            org.springframework.web.method.annotation.HandlerMethodValidationException.class
    })
    public ResponseEntity<ErrorResponse> handleValidationException(
            Exception exception,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        errorResponseMapper.build(
                                HttpStatus.BAD_REQUEST,
                                "Invalid request parameters",
                                request
                        )
                );
    }
}