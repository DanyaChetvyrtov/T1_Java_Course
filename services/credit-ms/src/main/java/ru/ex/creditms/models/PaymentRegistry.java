package ru.ex.creditms.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "payment_registry")
public class PaymentRegistry {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_registry_id", nullable = false)
    private ProductRegistry productRegistry;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "interest_rate_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal interestRateAmount;

    @Column(name = "debt_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal debtAmount;

    @Column(name = "expired", nullable = false)
    private Boolean expired = false;

    @Column(name = "payment_expiration_date", nullable = false)
    private LocalDate paymentExpirationDate;
}

