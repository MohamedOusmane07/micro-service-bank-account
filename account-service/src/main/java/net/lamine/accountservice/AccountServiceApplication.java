package net.lamine.accountservice;

import net.lamine.accountservice.clients.CustomerRestClient;
import net.lamine.accountservice.entities.BankAccount;
import net.lamine.accountservice.enums.AccountType;
import net.lamine.accountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){
        return args -> {
            customerRestClient.allCustomers().forEach(customer -> {
                BankAccount account1= BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .type(AccountType.CURRENT_ACCOUNT)
                        .balance(Math.random()*567875)
                        .customerId(customer.getId())
                        .createAt(LocalDate.now())
                        .currency("MAD")
                        .build();

                BankAccount account2= BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .type(AccountType.CURRENT_ACCOUNT)
                        .balance(Math.random()*4567884)
                        .customerId(customer.getId())
                        .createAt(LocalDate.now())
                        .currency("MAD")
                        .build();
                bankAccountRepository.save(account1);
                bankAccountRepository.save(account2 );

            });
        };
    }

}
