package ru.ex.accountms.models;

import jakarta.persistence.*;
import lombok.*;
import ru.ex.accountms.enums.CardStatus;
import ru.ex.accountms.enums.CardPaymentSystem;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "card")
public class Card {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "card_id", nullable = false, unique = true)
    private String cardId;

    @Column(name = "payment_system", nullable = false)
    private CardPaymentSystem cardPaymentSystem;

    @Column(name = "status", nullable = false)
    private CardStatus status;
}

