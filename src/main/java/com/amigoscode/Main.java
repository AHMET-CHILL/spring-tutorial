package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;
    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){

    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updatingCustomer(@PathVariable("customerId") Integer id,
                                @RequestBody NewCustomerRequest request) {

        Customer existCustomer = customerRepository.findById(id).orElse(null);
        if (Objects.nonNull(existCustomer)) {
            existCustomer.setName(request.name());
            existCustomer.setEmail(request.email());
            existCustomer.setAge(request.age());
            customerRepository.save(existCustomer);

        }else {
            System.out.println("Customer not found");
        }


    }

//    @GetMapping("/greet")
//    public GreetResponse great(){
////         GreetResponse  response= new GreetResponse(
////                "Hello!",
////                List.of("Java","Golang","Javascript"),
////                new Person("Alex",22,615)
////
////        );
////         return response;
//    }

//    record Person(String name,int age,double savings){}
//
//    record GreetResponse(
//            String greet, List<String> favProgrammingLanguages,
//            Person person
//            ){ }

    /*class GreetResponse{
        private final String greet;
        public GreetResponse(String greet) {
            this.greet = greet;
        }
        public String getGreet() {
            return greet;
        }

        @Override
        public String toString() {
            return "GreetResponse{"+
                    "greet="+greet+'\''+
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GreetResponse that = (GreetResponse) o;
            return Objects.equals(greet, that.greet);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(greet);
        }

     */


    }

