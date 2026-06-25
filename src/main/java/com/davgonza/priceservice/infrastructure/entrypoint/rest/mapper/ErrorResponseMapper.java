package com.davgonza.priceservice.infrastructure.entrypoint.rest.mapper;

import com.davgonza.priceservice.infrastructure.entrypoint.rest.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ErrorResponseMapper {

    public ErrorResponse build(
            HttpStatus status,
            String message,
            HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse();

        response.setTimestamp(
                OffsetDateTime.now()
        );

        response.setStatus(
                status.value()
        );

        response.setError(
                status.getReasonPhrase()
        );

        response.setMessage(
                message
        );

        response.setPath(
                request.getRequestURI()
        );

        return response;
    }
}