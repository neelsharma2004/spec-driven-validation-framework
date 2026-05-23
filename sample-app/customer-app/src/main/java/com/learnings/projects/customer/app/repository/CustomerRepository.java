package com.learnings.projects.customer.app.repository;

import com.learnings.projects.customer.app.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findFirstByExternalId(String externalId);
}
