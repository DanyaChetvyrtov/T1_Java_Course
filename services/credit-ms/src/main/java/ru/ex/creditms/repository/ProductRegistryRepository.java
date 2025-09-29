package ru.ex.creditms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ex.creditms.models.ProductRegistry;

@Repository
public interface ProductRegistryRepository extends JpaRepository<ProductRegistry, Long> {
}
