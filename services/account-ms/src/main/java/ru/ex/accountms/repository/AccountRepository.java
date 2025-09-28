package ru.ex.accountms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ex.accountms.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
