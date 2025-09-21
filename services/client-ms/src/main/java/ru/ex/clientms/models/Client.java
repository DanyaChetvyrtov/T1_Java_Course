package ru.ex.clientms.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "client", indexes = {
        @Index(columnList = "client_id", name = "idx_client_clientid")
})
public class Client {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    //- clientId (формат clientId: XXFFNNNNNNNN, где
    // XX - номер региона,
    // FF - номер подразделения банка,
    // N - порядковый.
    // Например, 770100000001, 770200000023)
    @Column(name = "client_id", nullable = false, unique = true)
    private String clientId;

    @Column(name = "region_code", nullable = false)
    @NotNull
    private Integer regionCode; // XX

    @Column(name = "branch_code", nullable = false)
    @NotNull
    private Integer branchCode; // FF

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents;

    public void addDocument(Document doc) {
        documents.add(doc);
        doc.setClient(this);
    }

}
