package com.davgonza.priceservice.infrastructure.persistence.price;

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
class PriceRepositoryAdapterTest {

    @Mock
    private SpringDataPriceRepository repository;

    @Mock
    private PriceEntityMapper mapper;

    @InjectMocks
    private PriceRepositoryAdapter adapter;

    @Test
    @DisplayName("Given existing price when finding applicable price then return domain price")
    void givenExistingPrice_whenFindingApplicablePrice_thenReturnDomainPrice() {

        // Given
        LocalDateTime date =
                LocalDateTime.of(2020, 6, 14, 16, 0);

        PriceEntity entity = PriceEntity.builder()
                .brandId(1L)
                .productId(35455L)
                .priceList(2)
                .priority(1)
                .price(new BigDecimal("25.45"))
                .currency("EUR")
                .startDate(date.minusHours(1))
                .endDate(date.plusHours(1))
                .build();

        Price expected = new Price(
                1L,
                35455L,
                2,
                1,
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPrice(),
                entity.getCurrency()
        );

        when(repository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                35455L,
                1L,
                date,
                date
        )).thenReturn(Optional.of(entity));

        when(mapper.toDomain(entity)).thenReturn(expected);

        // When
        Optional<Price> result =
                adapter.findApplicablePrice(date, 35455L, 1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());

        verify(repository).findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                35455L, 1L, date, date
        );

        verify(mapper).toDomain(entity);
    }

    @Test
    @DisplayName("Given no price when finding applicable price then return empty")
    void givenNoPrice_whenFindingApplicablePrice_thenReturnEmpty() {

        LocalDateTime date =
                LocalDateTime.of(2020, 6, 14, 16, 0);

        when(repository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                35455L,
                1L,
                date,
                date
        )).thenReturn(Optional.empty());

        Optional<Price> result =
                adapter.findApplicablePrice(date, 35455L, 1L);

        assertTrue(result.isEmpty());

        verify(repository).findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                35455L, 1L, date, date
        );

        verifyNoInteractions(mapper);
    }
}