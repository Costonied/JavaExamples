package ru.costonied.examples.spring.crud.repo;

import ru.costonied.examples.spring.crud.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}