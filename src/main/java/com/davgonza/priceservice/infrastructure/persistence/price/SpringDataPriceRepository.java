package com.davgonza.priceservice.infrastructure.persistence.price;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity>
    findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long productId,
            Long brandId,
            LocalDateTime applicationDateStart,
            LocalDateTime applicationDateEnd
    );
}