package com.api.customer.service;

import com.api.customer.entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(UUID id);
    Customer updateCustomer(UUID id, Customer customer);
    void deleteCustomer(UUID id);
}