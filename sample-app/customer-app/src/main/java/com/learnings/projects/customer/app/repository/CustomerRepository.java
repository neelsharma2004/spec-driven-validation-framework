package com.learnings.projects.customer.app.repository;

import com.learnings.projects.customer.app.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
}
