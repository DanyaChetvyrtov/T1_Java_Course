package ru.ex.clientms.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.ex.clientms.enums.Key;

import java.time.LocalDateTime;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(columnNames = "product_id")
})
public class Product {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_key", nullable = false)
    private Key key;

    // должен быть уникальный, т.е. нужно будет поставить
    // constraint на поле в бд
    //- productId (key + id)
    @Column(name = "product_id", nullable = false, unique = true)
    private String productId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}