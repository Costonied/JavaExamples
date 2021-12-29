package ru.costonied.examples.spring.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.costonied.examples.spring.crud.controller.request.CustomerRq;
import ru.costonied.examples.spring.crud.entity.Customer;
import ru.costonied.examples.spring.crud.repo.CustomerRepository;

import java.util.List;

@RestController
public class MainRESTController {
    private final CustomerRepository repository;

    @Autowired
    public MainRESTController(CustomerRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello";
    }

    @RequestMapping(value = "/customers", produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @PostMapping(value = "/customers")
    @ResponseBody
    public String addCustomer(@RequestBody CustomerRq customerRq) {
        System.out.println("Creating customer: " + customerRq.getFirstName() + " " + customerRq.getLastName());
        Customer customer = new Customer(customerRq.getFirstName(), customerRq.getLastName());
        return "Customer ID: " + repository.save(customer).getId();
    }

    @PutMapping(value = "/customers/{customerId}")
    @ResponseBody
    public String updateCustomer(@RequestBody CustomerRq customerRq, @PathVariable("customerId") Long customerId) {
        Customer customer = new Customer(customerId, customerRq.getFirstName(), customerRq.getLastName());
        repository.save(customer);
        return "Customer was updated";
    }

    @DeleteMapping(value = "/customers/{customerId}")
    @ResponseBody
    public String deleteCustomer(@PathVariable("customerId") Long customerId) {
        repository.deleteById(customerId);
        return "Customer was deleted";
    }
}
