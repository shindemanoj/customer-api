package com.api.customer.integration;

import com.api.customer.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/customers";
    }

    @Test
    void testCreateAndGetCustomer() throws Exception {
        // Create a new customer
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("John");
        newCustomer.setMiddleName("M");
        newCustomer.setLastName("Doe");
        newCustomer.setEmail("john.doe@example.com");
        newCustomer.setPhoneNumber("123-456-7890");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Customer> requestEntity = new HttpEntity<>(newCustomer, headers);

        // Send POST request
        ResponseEntity<Customer> createResponse = restTemplate.exchange(
                getBaseUrl(),
                HttpMethod.POST,
                requestEntity,
                Customer.class
        );

        // Verify POST response
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        Customer createdCustomer = createResponse.getBody();
        assertEquals("John", createdCustomer.getFirstName());

        // GET the customer by ID
        ResponseEntity<Customer> getResponse = restTemplate.exchange(
                getBaseUrl() + "/" + createdCustomer.getId(),
                HttpMethod.GET,
                null,
                Customer.class
        );

        // Verify GET response
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("John", getResponse.getBody().getFirstName());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        // Create a new customer
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Jane");
        newCustomer.setLastName("Smith");
        newCustomer.setEmail("jane.smith@example.com");
        newCustomer.setPhoneNumber("987-654-3210");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Customer> requestEntity = new HttpEntity<>(newCustomer, headers);

        // Send POST request
        ResponseEntity<Customer> createResponse = restTemplate.exchange(
                getBaseUrl(),
                HttpMethod.POST,
                requestEntity,
                Customer.class
        );

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        UUID customerId = createResponse.getBody().getId();

        // DELETE the customer
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                getBaseUrl() + "/" + customerId,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        // Verify DELETE response
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

        // Attempt to GET the deleted customer
        ResponseEntity<String> getResponse = restTemplate.exchange(
                getBaseUrl() + "/" + customerId,
                HttpMethod.GET,
                null,
                String.class
        );

        // Verify that the customer is not found
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
}