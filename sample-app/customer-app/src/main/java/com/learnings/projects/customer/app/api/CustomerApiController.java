package com.learnings.projects.customer.app.api;

import com.learnings.projects.customer.api.CustomerApi;
import com.learnings.projects.customer.model.Customer;
import com.learnings.projects.customer.model.CustomerCreate;
import com.learnings.projects.customer.app.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerApiController implements CustomerApi {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<Customer> createCustomer(CustomerCreate customerCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerCreate));
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Customer>> listCustomers() {
        return ResponseEntity.ok(customerService.listCustomers());
    }

    @Override
    public ResponseEntity<Customer> retrieveCustomer(String id) {
        return ResponseEntity.ok(customerService.retrieveCustomer(id));
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(String id, Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }
}
