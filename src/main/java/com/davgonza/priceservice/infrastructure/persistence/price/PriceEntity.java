package com.davgonza.priceservice.infrastructure.persistence.price;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long brandId;
    private Long productId;
    private Integer priceList;
    private Integer priority;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private BigDecimal price;
    private String currency;
}