package com.Solution.CRM.service;

import com.Solution.CRM.model.Address;
import com.Solution.CRM.model.Customer;
import com.Solution.CRM.repository.AddressRepository;
import com.Solution.CRM.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntityFinderService implements EntityFinder {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Customer customerByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(new Customer());
    }

    @Override
    public List<Address> addressByCustomer(Customer customer) {
        return addressRepository.findByCustomer(customer);
    }
}
