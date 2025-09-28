package ru.ex.clientms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ex.clientms.models.ClientProduct;

@Repository
public interface ClientProductRepository extends JpaRepository<ClientProduct, Long> { }
