package ru.ex.creditms.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "product_registry")
public class ProductRegistry {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "interest_rate", precision = 5, scale = 2, nullable = false)
    private BigDecimal interestRate;

    @Column(name = "open_date", nullable = false)
    private LocalDate openDate;

    @OneToMany(mappedBy = "productRegistry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentRegistry> payments = new ArrayList<>();
}

