package com.davgonza.priceservice.application.service;

import com.davgonza.priceservice.application.port.in.GetApplicablePriceUseCase;
import com.davgonza.priceservice.application.port.out.PriceRepository;
import com.davgonza.priceservice.domain.exception.PriceNotFoundException;
import com.davgonza.priceservice.domain.model.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetApplicablePriceService implements GetApplicablePriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public Price getApplicablePrice(
            LocalDateTime applicationDate,
            Long productId,
            Long brandId) {

        Objects.requireNonNull(
                applicationDate,
                "applicationDate must not be null"
        );

        Objects.requireNonNull(
                productId,
                "productId must not be null"
        );

        Objects.requireNonNull(
                brandId,
                "brandId must not be null"
        );

        log.info(
                "Searching applicable price. applicationDate={}, productId={}, brandId={}",
                applicationDate,
                productId,
                brandId
        );

        Price price = priceRepository
                .findApplicablePrice(
                        applicationDate,
                        productId,
                        brandId
                )
                .orElseThrow(() -> {

                    log.warn(
                            "Applicable price not found. applicationDate={}, productId={}, brandId={}",
                            applicationDate,
                            productId,
                            brandId
                    );

                    return new PriceNotFoundException(
                            "Applicable price not found"
                    );
                });

        log.info(
                "Applicable price found. productId={}, brandId={}, priceList={}, price={}",
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getPrice()
        );

        return price;
    }
}