package com.fundaleonREST.fundaleonapirest.repository;

import com.fundaleonREST.fundaleonapirest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findById(UUID id);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findCustomerByIdentification(String identification);
    Optional<Customer> findCustomerByEmail(String email);
    @Override
    @Query("UPDATE customer set status = false WHERE id = ?1")
    void deleteById(UUID id);
}
