package ru.ex.creditms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ex.creditms.models.PaymentRegistry;

@Repository
public interface PaymentRegistryRepository extends JpaRepository<PaymentRegistry, Long> {
}
