package ru.ex.clientms.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.ex.clientms.enums.Key;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(columnNames = "product_id")
})
public class Product {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_key", nullable = false)
    private Key key;

    @Column(name = "product_id", nullable = false, unique = true)
    private String productId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        generateProductId();
    }

    private void generateProductId() {
        if (this.productId == null && this.id != null && this.key != null)
            this.productId = this.key.toString() + this.id;
    }
}