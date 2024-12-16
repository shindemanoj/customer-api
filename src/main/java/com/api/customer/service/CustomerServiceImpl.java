package com.api.customer.service;

import com.api.customer.entity.Customer;
import com.api.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        return repository.findById(id).map(existingCustomer -> {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setMiddleName(customer.getMiddleName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            return repository.save(existingCustomer);
        }).orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public void deleteCustomer(UUID id) {
        repository.deleteById(id);
    }
}