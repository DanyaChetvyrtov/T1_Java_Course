package ru.ex.accountms.models;

import jakarta.persistence.*;
import lombok.*;
import ru.ex.accountms.enums.AccountStatus;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "account")
public class Account {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "balance", precision = 15, scale = 2, nullable = false)
    private BigDecimal balance;

    @Column(name = "interest_rate", precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "is_recalc", nullable = false)
    private Boolean isRecalc = false;

    @Column(name = "card_exist", nullable = false)
    private Boolean cardExist = false;

    @Column(name = "status", nullable = false)
    private AccountStatus status;
}

