package net.lamine.customerservice;

import net.lamine.customerservice.entities.Customer;
import net.lamine.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {

            List<Customer> customerList=List.of(
                    Customer.builder()
                            .firstName("Ousmane")
                            .lastName("Mohamed")
                            .email("ousib@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("Ousmane")
                            .lastName("Lamine")
                            .email("ousib@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("Ousmane")
                            .lastName("Ibrahim")
                            .email("ousib@gmail.com")
                            .build()


            );
            customerRepository.saveAll(customerList);


        };
    }

}
