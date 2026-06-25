package com.davgonza.priceservice.infrastructure.persistence.price;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SpringDataPriceRepositoryTest {

    @Autowired
    private SpringDataPriceRepository repository;

    @Test
    @DisplayName("Given 10:00 when searching then return price list 1")
    void given1000_whenSearching_thenReturnPriceList1() {

        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        var result = repository
                .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        35455L,
                        1L,
                        date,
                        date
                );

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getPriceList());
    }

    @Test
    @DisplayName("Given 16:00 when searching then return price list 2")
    void given1600_whenSearching_thenReturnPriceList2() {

        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);

        var result = repository
                .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        35455L,
                        1L,
                        date,
                        date
                );

        assertTrue(result.isPresent());
        assertEquals(2, result.get().getPriceList());
    }

    @Test
    @DisplayName("Given 21:00 when searching then return price list 1")
    void given2100_whenSearching_thenReturnPriceList1() {

        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0);

        var result = repository
                .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        35455L,
                        1L,
                        date,
                        date
                );

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getPriceList());
    }

    @Test
    @DisplayName("Given 2020-06-15 10:00 then return price list 3")
    void given15061000_whenSearching_thenReturnPriceList3() {

        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0);

        var result = repository
                .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        35455L,
                        1L,
                        date,
                        date
                );

        assertTrue(result.isPresent());
        assertEquals(3, result.get().getPriceList());
    }

    @Test
    @DisplayName("Given 2020-06-16 21:00 then return price list 4")
    void given16062100_whenSearching_thenReturnPriceList4() {

        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0);

        var result = repository
                .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        35455L,
                        1L,
                        date,
                        date
                );

        assertTrue(result.isPresent());
        assertEquals(4, result.get().getPriceList());
    }
}