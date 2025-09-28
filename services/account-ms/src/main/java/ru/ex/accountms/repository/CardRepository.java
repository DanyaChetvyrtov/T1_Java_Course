package ru.ex.accountms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ex.accountms.models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
