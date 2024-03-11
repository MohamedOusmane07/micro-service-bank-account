package net.lamine.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.lamine.accountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient  {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);


    @GetMapping("/customers")
    @CircuitBreaker(name="customerService", fallbackMethod = "getAllDefaultCustomer")
    List<Customer> allCustomers();
    default Customer getDefaultCustomer(Long id,Exception exception){
        Customer customer=new Customer();
        customer.setId(id);
        customer.setFirstName("Not vailable");
        customer.setLastName("Not vailable");
        customer.setEmail("Not vailable");
        System.out.println("Error getting customer:"+exception.getMessage());
        return customer;
    }

    default List<Customer> getAllDefaultCustomer(){
        return List.of();

    }
}
