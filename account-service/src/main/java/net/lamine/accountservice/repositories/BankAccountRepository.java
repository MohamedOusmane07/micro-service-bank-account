package net.lamine.accountservice.repositories;

import net.lamine.accountservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
