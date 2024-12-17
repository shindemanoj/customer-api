package com.api.customer.unit;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.api.customer.controller.CustomerController;
import com.api.customer.entity.Customer;
import com.api.customer.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void testCreateCustomer() throws Exception {
        Customer customer = new Customer(UUID.randomUUID(), "John", null, "Doe", "john@example.com", "1234567890");

        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        String json = """
                {
                    "firstName": "John",
                    "middleName": null,
                    "lastName": "Doe",
                    "email": "john@example.com",
                    "phoneNumber": "1234567890"
                }
                """;

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }
}