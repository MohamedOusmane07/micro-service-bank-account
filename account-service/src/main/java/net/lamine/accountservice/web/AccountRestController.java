package net.lamine.accountservice.web;

import net.lamine.accountservice.clients.CustomerRestClient;
import net.lamine.accountservice.entities.BankAccount;
import net.lamine.accountservice.model.Customer;
import net.lamine.accountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository bankAccountRepository,
                                 CustomerRestClient customerRestClient){
        this.bankAccountRepository=bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }
    @GetMapping("/accounts")
    public List<BankAccount> accountList(){

        List<BankAccount> accountList=bankAccountRepository.findAll();
        accountList.forEach(account->{
            account.setCustomer(customerRestClient.findCustomerById(account.getCustomerId()));
        });
        return accountList;
    }

    @GetMapping("/accounts/{accountId}")
    public BankAccount bankAccount(@PathVariable String accountId){
        //On recupère le compte à partir de la base des données
        BankAccount bankAccount=bankAccountRepository.findById(accountId).get();
        //On recupère un customer à partir du micro service CUSTOMER-SERVICE.
        Customer customer=customerRestClient.findCustomerById(bankAccount.getCustomerId());
        //On modifie notre bank account
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

}
