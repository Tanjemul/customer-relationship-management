package com.Solution.CRM.repository;

import com.Solution.CRM.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, BigInteger> {
    Optional<Customer> findByEmail(String email);
}
