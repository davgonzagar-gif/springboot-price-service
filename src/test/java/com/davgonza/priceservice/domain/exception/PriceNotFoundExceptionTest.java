package com.davgonza.priceservice.domain.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@DisplayName("Price Not Found Exception Tests")
class PriceNotFoundExceptionTest {

    @Test
    @DisplayName("Given an error message when creating the exception then the message is preserved")
    void givenErrorMessage_whenCreatingException_thenMessageIsPreserved() {

        String message = "Price not found";

        PriceNotFoundException exception = new PriceNotFoundException(message);

        assertAll(
                () -> assertEquals(message, exception.getMessage()),
                () -> assertInstanceOf(RuntimeException.class, exception)
        );
    }
}