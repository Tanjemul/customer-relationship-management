package com.Solution.CRM.repository;

import com.Solution.CRM.model.Address;
import com.Solution.CRM.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, BigInteger> {
    List<Address> findByCustomer(Customer customer);
}
