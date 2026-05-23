package com.learnings.projects.customer.app.service;

import com.learnings.projects.customer.app.entity.CustomerEntity;
import com.learnings.projects.customer.app.mapper.CustomerMapper;
import com.learnings.projects.customer.model.Customer;
import com.learnings.projects.customer.model.CustomerCreate;
import com.learnings.projects.customer.app.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = new CustomerMapper();
    }

    @Transactional(readOnly = true)
    public List<Customer> listCustomers() {
        return customerMapper.toListResponse(customerRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Customer retrieveCustomer(String id) {
        return customerMapper.toModel(findEntity(id));
    }

    @Transactional
    public Customer createCustomer(CustomerCreate customerCreate) {
        CustomerEntity entity = customerMapper.toEntity(customerCreate);
        entity.setId("cust-" + UUID.randomUUID());
        entity.setHref("/customers/" + entity.getId());
        customerRepository.save(entity);
        customerMapper.syncRelated(entity, toCustomerModel(customerCreate));
        customerRepository.save(entity);
        return customerMapper.toModel(entity);
    }

    @Transactional
    public Customer updateCustomer(String id, Customer customer) {
        CustomerEntity entity = findEntity(id);
        customerMapper.merge(entity, customer);
        customerMapper.syncRelated(entity, customer);
        entity.setHref("/customers/" + id);
        customerRepository.save(entity);
        return customerMapper.toModel(entity);
    }

    @Transactional
    public void deleteCustomer(String id) {
        CustomerEntity entity = findEntity(id);
        customerRepository.delete(entity);
    }

    private CustomerEntity findEntity(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
    }

    private Customer toCustomerModel(CustomerCreate source) {
        Customer customer = new Customer();
        customer.setName(source.getName());
        customer.setStatus(source.getStatus());
        customer.setExternalId(source.getExternalId());
        customer.setDescription(source.getDescription());
        return customer;
    }
}
