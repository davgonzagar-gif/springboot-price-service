package com.davgonza.priceservice.infrastructure.entrypoint.rest.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PricesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given 2020-06-14 10:00 when requesting applicable price then return price list 1")
    void given202006141000_whenRequestingApplicablePrice_thenReturnPriceList1() throws Exception {

        // Given
        String applicationDate = "2020-06-14T10:00:00Z";

        // When / Then
        assertApplicablePrice(
                applicationDate,
                1,
                35.50
        );
    }

    @Test
    @DisplayName("Given 2020-06-14 16:00 when requesting applicable price then return price list 2")
    void given202006141600_whenRequestingApplicablePrice_thenReturnPriceList2() throws Exception {

        // Given
        String applicationDate = "2020-06-14T16:00:00Z";

        // When / Then
        assertApplicablePrice(
                applicationDate,
                2,
                25.45
        );
    }

    @Test
    @DisplayName("Given 2020-06-14 21:00 when requesting applicable price then return price list 1")
    void given202006142100_whenRequestingApplicablePrice_thenReturnPriceList1() throws Exception {

        // Given
        String applicationDate = "2020-06-14T21:00:00Z";

        // When / Then
        assertApplicablePrice(
                applicationDate,
                1,
                35.50
        );
    }

    @Test
    @DisplayName("Given 2020-06-15 10:00 when requesting applicable price then return price list 3")
    void given202006151000_whenRequestingApplicablePrice_thenReturnPriceList3() throws Exception {

        // Given
        String applicationDate = "2020-06-15T10:00:00Z";

        // When / Then
        assertApplicablePrice(
                applicationDate,
                3,
                30.50
        );
    }

    @Test
    @DisplayName("Given 2020-06-16 21:00 when requesting applicable price then return price list 4")
    void given202006162100_whenRequestingApplicablePrice_thenReturnPriceList4() throws Exception {

        // Given
        String applicationDate = "2020-06-16T21:00:00Z";

        // When / Then
        assertApplicablePrice(
                applicationDate,
                4,
                38.95
        );
    }

    @Test
    @DisplayName("Given non existing price when requesting applicable price then return not found")
    void givenNonExistingPrice_whenRequestingApplicablePrice_thenReturnNotFound() throws Exception {

        // Given
        String applicationDate = "2026-01-01T10:00:00Z";

        // When / Then
        mockMvc.perform(
                        get("/api/v1/prices")
                                .param("applicationDate", applicationDate)
                                .param("productId", "99999")
                                .param("brandId", "1")
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Applicable price not found"))
                .andExpect(jsonPath("$.path").value("/api/v1/prices"));
    }

    private void assertApplicablePrice(
            String applicationDate,
            int expectedPriceList,
            double expectedPrice) throws Exception {

        mockMvc.perform(
                        get("/api/v1/prices")
                                .param("applicationDate", applicationDate)
                                .param("productId", "35455")
                                .param("brandId", "1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(expectedPriceList))
                .andExpect(jsonPath("$.price").value(expectedPrice))
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists());
    }
}