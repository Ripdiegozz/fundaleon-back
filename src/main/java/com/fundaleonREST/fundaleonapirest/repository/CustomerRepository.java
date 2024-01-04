package com.fundaleonREST.fundaleonapirest.repository;

import com.fundaleonREST.fundaleonapirest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findById(UUID id);
    Optional<Customer> findByEmail(String email);
    void deleteById(UUID id);
}
