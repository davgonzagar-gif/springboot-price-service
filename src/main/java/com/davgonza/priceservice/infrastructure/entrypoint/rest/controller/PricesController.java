package com.davgonza.priceservice.infrastructure.entrypoint.rest.controller;

import com.davgonza.priceservice.infrastructure.entrypoint.rest.api.PricesApi;
import com.davgonza.priceservice.infrastructure.entrypoint.rest.model.ApplicablePrice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@RestController
public class PricesController implements PricesApi {

    @Override
    public ResponseEntity<ApplicablePrice> findApplicablePrice(
            OffsetDateTime applicationDate,
            Long productId,
            Long brandId) {

        ApplicablePrice response = new ApplicablePrice();

        response.setProductId(productId);
        response.setBrandId(brandId);
        response.setPriceList(1);
        response.setPrice(35.50);
        response.setCurrency("EUR");

        return ResponseEntity.ok(response);
    }
}