package com.Solution.CRM.service;

import com.Solution.CRM.model.Address;
import com.Solution.CRM.model.Customer;

import java.math.BigInteger;
import java.util.List;

public interface EntityFinder {
    Customer customerByEmail(String email);
    List<Address> addressByCustomer(Customer customer);
}
