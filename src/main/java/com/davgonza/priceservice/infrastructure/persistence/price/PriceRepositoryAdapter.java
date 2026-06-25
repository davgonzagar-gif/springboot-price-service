package com.davgonza.priceservice.infrastructure.persistence.price;

import com.davgonza.priceservice.application.port.out.PriceRepository;
import com.davgonza.priceservice.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final SpringDataPriceRepository repository;
    private final PriceEntityMapper mapper;

    @Override
    public Optional<Price> findApplicablePrice(
            LocalDateTime applicationDate,
            Long productId,
            Long brandId) {

        return repository
                .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        productId,
                        brandId,
                        applicationDate,
                        applicationDate
                )
                .map(mapper::toDomain);
    }
}