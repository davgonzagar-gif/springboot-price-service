package com.davgonza.priceservice.application.service;

import com.davgonza.priceservice.application.port.out.PriceRepository;
import com.davgonza.priceservice.domain.exception.PriceNotFoundException;
import com.davgonza.priceservice.domain.model.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetApplicablePriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private GetApplicablePriceService service;

    @Test
    @DisplayName("Given existing applicable price when getting applicable price then return price")
    void givenExistingApplicablePrice_whenGettingApplicablePrice_thenReturnPrice() {

        // Given
        LocalDateTime applicationDate =
                LocalDateTime.of(2020, 6, 14, 10, 0);

        Long productId = 35455L;
        Long brandId = 1L;

        Price expectedPrice = new Price(
                brandId,
                productId,
                1,
                0,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                new BigDecimal("35.50"),
                "EUR"
        );

        when(priceRepository.findApplicablePrice(
                applicationDate,
                productId,
                brandId))
                .thenReturn(Optional.of(expectedPrice));

        // When
        Price result = service.getApplicablePrice(
                applicationDate,
                productId,
                brandId);

        // Then
        assertEquals(expectedPrice, result);

        verify(priceRepository)
                .findApplicablePrice(applicationDate, productId, brandId);
    }

    @Test
    @DisplayName("Given no applicable price when getting applicable price then throw PriceNotFoundException")
    void givenNoApplicablePrice_whenGettingApplicablePrice_thenThrowPriceNotFoundException() {

        // Given
        LocalDateTime applicationDate =
                LocalDateTime.of(2020, 6, 14, 10, 0);

        Long productId = 35455L;
        Long brandId = 1L;

        when(priceRepository.findApplicablePrice(
                applicationDate,
                productId,
                brandId))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                PriceNotFoundException.class,
                () -> service.getApplicablePrice(
                        applicationDate,
                        productId,
                        brandId)
        );

        verify(priceRepository)
                .findApplicablePrice(applicationDate, productId, brandId);
    }

    @Test
    @DisplayName("Given null application date when getting applicable price then throw NullPointerException")
    void givenNullApplicationDate_whenGettingApplicablePrice_thenThrowNullPointerException() {

        // When / Then
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> service.getApplicablePrice(
                        null,
                        35455L,
                        1L)
        );

        assertEquals("applicationDate must not be null", exception.getMessage());

        verifyNoInteractions(priceRepository);
    }

    @Test
    @DisplayName("Given null product id when getting applicable price then throw NullPointerException")
    void givenNullProductId_whenGettingApplicablePrice_thenThrowNullPointerException() {

        // Given
        LocalDateTime applicationDate =
                LocalDateTime.of(2020, 6, 14, 10, 0);

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> service.getApplicablePrice(
                        applicationDate,
                        null,
                        1L)
        );

        assertEquals("productId must not be null", exception.getMessage());

        verifyNoInteractions(priceRepository);
    }

    @Test
    @DisplayName("Given null brand id when getting applicable price then throw NullPointerException")
    void givenNullBrandId_whenGettingApplicablePrice_thenThrowNullPointerException() {

        // Given
        LocalDateTime applicationDate =
                LocalDateTime.of(2020, 6, 14, 10, 0);

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> service.getApplicablePrice(
                        applicationDate,
                        35455L,
                        null)
        );

        assertEquals("brandId must not be null", exception.getMessage());

        verifyNoInteractions(priceRepository);
    }
}