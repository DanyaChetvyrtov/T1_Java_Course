package ru.ex.clientms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ex.clientms.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByClientId(String clientId);
    Long countByRegionCodeAndBranchCode(Integer regionCode, Integer branchCode);
}
